package com.jiuxian.core.dao;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryCondition implements Specification {
    private List<QueryCondition> queryConditions = new ArrayList<>();
    private List<String> groupByFields = new ArrayList<>();
    private List<OrderBy> orderByList = new ArrayList<>();
    private String fileName;
    private Operator operator;
    private Object value;
    private Object value2;
    private MatchMode matchMode;

    public QueryCondition(String fileName, Operator operator, Object value) {
        this.fileName = fileName;
        this.operator = operator;
        this.value = value;
        this.matchMode = MatchMode.ANYWHERE;
    }

    /**
     * isnull,isnotnull,isEmpty,isnotEmpty
     *
     * @param fileName
     * @param operator
     */
    public QueryCondition(String fileName, Operator operator) {
        this.fileName = fileName;
        this.operator = operator;
    }

    /**
     * between
     *
     * @param fileName
     * @param value1
     * @param value2
     */
    public QueryCondition(String fileName, Object value1, Object value2) {
        this.fileName = fileName;
        this.operator = Operator.BETWEEN;
        this.value = value1;
        this.value2 = value2;
    }

    public QueryCondition(String fileName, Operator operator, Object value, MatchMode matchMode) {
        this.fileName = fileName;
        this.operator = operator;
        this.value = value;
        this.matchMode = matchMode;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder builder) {
        List<Order> orders = new ArrayList<>();
        orderByList.forEach(order -> orders.add(getOrder(order.getOrderEnum(), builder, root.get(order.getFileName()))));
        query.orderBy(orders);

        List<Expression<?>> expressions = new ArrayList<>();
        groupByFields.forEach(groupBy -> expressions.add(root.get(groupBy)));
        query.groupBy(expressions);

        List<Predicate> predicateList = new ArrayList<>();
        queryConditions.forEach(condition -> {
            Predicate predicate = getBuilder(condition.getOperator(), builder, root.get(condition.getFileName()), condition.getValue(), condition.getMatchMode());
            predicateList.add(predicate);
        });
        return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }

    private Order getOrder(OrderEnum orderBy, CriteriaBuilder builder, Expression expression) {
        switch (orderBy) {
            case ASC:
                return builder.asc(expression);
            case DESC:
                return builder.desc(expression);
            default:
                return builder.asc(expression);
        }
    }

    private Predicate getBuilder(Operator operator, CriteriaBuilder builder, Expression expression, Object value, MatchMode matchMode) {
        switch (operator) {
            case EQ:
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                switch (matchMode) {
                    case START:
                        return builder.like((Expression<String>) expression, value + "%");
                    case END:
                        return builder.like((Expression<String>) expression, "%" + value);
                    case ANYWHERE:
                        return builder.like((Expression<String>) expression, "%" + value + "%");
                    default:
                        return builder.like((Expression<String>) expression, "%" + value + "%");
                }
            case LT:
                return builder.lessThan(expression, (Comparable) value);
            case GT:
                return builder.greaterThan(expression, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            case ISNULL:
                return builder.isNull(expression);
            case ISNOTNULL:
                return builder.isNotNull(expression);
            case ISEMPTY:
                return builder.isEmpty(expression);
            case ISNOTEMPTY:
                return builder.isNotEmpty(expression);
            default:
                return null;
        }
    }


    public List<String> getGroupByFields() {
        return groupByFields;
    }

    public void setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
    }

    public List<QueryCondition> getQueryConditions() {
        return queryConditions;
    }

    public void setQueryConditions(List<QueryCondition> queryConditions) {
        this.queryConditions = queryConditions;
    }

    public List<OrderBy> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<OrderBy> orderByList) {
        this.orderByList = orderByList;
    }


    public QueryCondition(QueryCondition... queryConditions) {
        Arrays.asList(queryConditions).forEach(queryCondition -> this.queryConditions.add(queryCondition));
    }

    public QueryCondition(List<QueryCondition> queryConditions, List<OrderBy> orderByList) {
        this.queryConditions = queryConditions;
        this.orderByList = orderByList;
    }

    public QueryCondition(List<QueryCondition> queryConditions, List<String> groupByFields, List<OrderBy> orderByList) {
        this.queryConditions = queryConditions;
        this.groupByFields = groupByFields;
        this.orderByList = orderByList;
    }


    public String getFileName() {
        return fileName;
    }

    public Operator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public MatchMode getMatchMode() {
        return matchMode;
    }

}


