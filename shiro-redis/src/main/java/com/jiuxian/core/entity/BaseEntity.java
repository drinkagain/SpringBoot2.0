package com.jiuxian.core.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseEntity implements Serializable, Cloneable {
    public abstract Long getId();
    public abstract void setId(Long id);

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    public BaseEntity clone() {
        try {
            return (BaseEntity) super.clone();
        } catch (CloneNotSupportedException e) {
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
            Long thisId = this.getId(), otherId = other.getId();
            if (thisId == null || otherId == null) {
                return false;
            } else if (thisId.equals(otherId))
                return true;
        }
        return false;
    }

    public String toString() {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("\n    ");
            sb.append(this.getClass().getSimpleName());
            sb.append(": ");

            for (Method m : this.getClass().getMethods()) {
                if (!m.getName().startsWith("get") || m.getName().equals("getClass"))
                    continue;
                Class<?> t = m.getReturnType();
                if (t == String.class || t == Integer.TYPE || t == Integer.class || t == Long.TYPE || t == Long.class || t == Float.TYPE || t == Float.class || t == Double.TYPE
                        || t == Double.class) {
                    if (m.getParameterTypes() == null || m.getParameterTypes().length == 0) {
                        sb.append(m.getName().substring(3));
                        sb.append('=');
                        sb.append(m.invoke(this, (Object[]) null));
                        sb.append(',');
                    }
                }
            }
            return sb.toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
