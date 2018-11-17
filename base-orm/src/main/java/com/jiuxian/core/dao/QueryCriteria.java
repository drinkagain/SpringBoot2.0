package com.jiuxian.core.dao;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryCriteria implements Specification {
    private List<QueryCriteria> queryCriteria = new ArrayList<>();
    private List<String> groupByFields = new ArrayList<>();
    private List<OrderBy> orderByList = new ArrayList<>();
    private String fileName;
    private Operator operator;
    private Object value;
    private Object value2;
    private MatchMode matchMode;

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder builder) {
        List<Order> orders = new ArrayList<>();
        orderByList.forEach(order -> orders.add(getOrder(order.getOrderEnum(), builder, root.get(order.getFileName()))));
        query.orderBy(orders);

        List<Expression<?>> expressions = new ArrayList<>();
        groupByFields.forEach(groupBy -> expressions.add(root.get(groupBy)));
        query.groupBy(expressions);

        List<Predicate> predicateList = new ArrayList<>();
        queryCriteria.forEach(condition -> {
            Predicate predicate = getBuilder(condition.operator, builder, root.get(condition.fileName), condition.value, condition.matchMode);
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
            case IS_NOT_NULL:
                return builder.isNotNull(expression);
            case IS_EMPTY:
                return builder.isEmpty(expression);
            case IS_NOT_EMPTY:
                return builder.isNotEmpty(expression);
            case BETWEEN:
                return builder.between(expression, (Comparable) value, (Comparable) value2);
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

    public List<QueryCriteria> getQueryCriteria() {
        return queryCriteria;
    }

    public void setQueryCriteria(List<QueryCriteria> queryCriteria) {
        this.queryCriteria = queryCriteria;
    }

    public List<OrderBy> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<OrderBy> orderByList) {
        this.orderByList = orderByList;
    }


    public QueryCriteria(QueryCriteria... queryCriterias) {
        Arrays.asList(queryCriterias).forEach(queryCriteria -> this.queryCriteria.add(queryCriteria));
    }

    public QueryCriteria(List<QueryCriteria> queryCriteria, List<OrderBy> orderByList) {
        this.queryCriteria = queryCriteria;
        this.orderByList = orderByList;
    }

    public QueryCriteria(List<QueryCriteria> queryCriteria, List<String> groupByFields, List<OrderBy> orderByList) {
        this.queryCriteria = queryCriteria;
        this.groupByFields = groupByFields;
        this.orderByList = orderByList;
    }


    public QueryCriteria(String fileName, Operator operator, Object value) {
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
    public QueryCriteria(String fileName, Operator operator) {
        this.fileName = fileName;
        this.operator = operator;
    }

    public QueryCriteria(String fileName, Object value1, Operator operator) {
        this.fileName = fileName;
        this.operator = operator;
        this.value = value1;
    }

    /**
     * between
     *
     * @param fileName
     * @param value1
     * @param value2
     */
    public QueryCriteria(String fileName, Object value1, Object value2) {
        this.fileName = fileName;
        this.operator = Operator.BETWEEN;
        this.value = value1;
        this.value2 = value2;
    }

    public QueryCriteria(String fileName, Operator operator, Object value, MatchMode matchMode) {
        this.fileName = fileName;
        this.operator = operator;
        this.value = value;
        this.matchMode = matchMode;
    }
}


