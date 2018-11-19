package com.jiuxian.dao;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑条件表达式 用于复杂条件时使用，如但属性多对应值的OR查询等
 */
public class LogicalExpression<T> implements Specification<T> {
    private Operator operator;      //计算符
    private String fileName;
    private Object value1;
    private Object value2;
    private QueryCondition[] QueryCondition;

    public LogicalExpression(QueryCondition[] querySpecification, Operator operator) {
        this.QueryCondition = querySpecification;
        this.operator = operator;
    }

    //between用构造方法
    public LogicalExpression(String fileName, Object value1, Object value2, Operator operator) {
        this.fileName = fileName;
        this.value1 = value1;
        this.value2 = value2;
        this.operator = operator;
        this.QueryCondition = null;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (QueryCondition != null) {
            for (int i = 0; i < QueryCondition.length; i++) {
                predicates.add(QueryCondition[i].toPredicate(root, query, builder));
            }
        }
        switch (operator) {
            case OR:
                return builder.or(predicates.toArray(new Predicate[predicates.size()]));
            case AND:
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            case BETWEEN:
                Expression expression = root.get(fileName);
                return builder.between(expression, (Comparable) value1, (Comparable) value2);
            default:
                return null;
        }
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Object getValue1() {
        return value1;
    }

    public void setValue1(Object value1) {
        this.value1 = value1;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }
}