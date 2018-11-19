package com.jiuxian.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private Date createTime;
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S", locale = "zh", timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S", locale = "zh", timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((getId() == null) ? 0 : getId().hashCode());
    }

    public BaseEntity clone() {
        try {
            return (BaseEntity) super.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof BaseEntity) {
            BaseEntity other = (BaseEntity) obj;
            if (!this.getClass().isAssignableFrom(other.getClass()))
                return false;
            String thisId = this.getId(), otherId = other.getId();
            if (thisId == null || otherId == null) {
                return false;
            } else return thisId.equals(otherId);
        }
        return false;
    }

    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("\n    ");
            sb.append(this.getClass().getSimpleName());
            sb.append(": ");

            for (Method m : this.getClass().getMethods()) {
                if (!m.getName().startsWith("get") || m.getName().equals("getClass"))
                    continue;
                Class<?> t = m.getReturnType();
                if (t == String.class || t == Integer.TYPE || t == Integer.class || t == Long.TYPE
                        || t == Long.class || t == Float.TYPE || t == Float.class || t == Double.TYPE
                        || t == Double.class || t == Date.class || t == BigDecimal.class) {
                    if (m.getParameterTypes().length == 0) {
                        sb.append(m.getName().substring(3));
                        sb.append('=');
                        sb.append(m.invoke(this, (Object[]) null));
                        sb.append(',');
                    }
                }
            }
            return sb.toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
