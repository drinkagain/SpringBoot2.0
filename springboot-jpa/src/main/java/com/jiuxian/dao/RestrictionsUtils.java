package com.jiuxian.dao;

import java.util.Collection;

/**
 * 条件构造器
 * 用于创建条件表达式
 */
public class RestrictionsUtils {

    /**
     * 不为空
     * @param fieldName : 匹配字段
     */
    public static QueryCriteria isNotEmpty(String fieldName) {
        return new QueryCriteria(fieldName, Operator.IS_NOT_EMPTY);
    }

    /**
     * 为空
     * @param fieldName : 匹配字段
     */
    public static QueryCriteria isEmpty(String fieldName) {
        return new QueryCriteria(fieldName, Operator.IS_EMPTY);
    }

    /**
     * 为空
     * @param fieldName : 匹配字段
     */
    public static QueryCriteria isNull(String fieldName) {
        return new QueryCriteria(fieldName, Operator.ISNULL);
    }

    /**
     * 不为空
     * @param fieldName : 匹配字段
     */
    public static QueryCriteria isNotNull(String fieldName) {
        return new QueryCriteria(fieldName, Operator.IS_NOT_NULL);
    }

    /**
     * 等于
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static QueryCriteria eq(String fieldName, Object value) {
        return new QueryCriteria(fieldName, value, Operator.EQ);
    }

    /**
     * 不等于
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static QueryCriteria ne(String fieldName, Object value) {
        return new QueryCriteria(fieldName, value, Operator.NE);
    }

    /**
     * 模糊匹配
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static QueryCriteria like(String fieldName, String value) {
        return new QueryCriteria(fieldName, Operator.LIKE, value, MatchMode.ANYWHERE);
    }


    /**
     * 自定义模式模糊匹配
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @param matchMode : 匹配方式(MatchMode.START\END\ANYWHERE)
     */
    public static QueryCriteria like(String fieldName, String value,
                                     MatchMode matchMode) {
        return new QueryCriteria(fieldName, Operator.LIKE, value, matchMode);
    }

    /**
     * 大于
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static QueryCriteria gt(String fieldName, Object value) {
        return new QueryCriteria(fieldName, value, Operator.GT);
    }


    /**
     * 小于
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static QueryCriteria lt(String fieldName, Object value) {
        return new QueryCriteria(fieldName, value, Operator.LT);
    }


    /**
     * 小于等于
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static QueryCriteria lte(String fieldName, Object value) {
        return new QueryCriteria(fieldName, value, Operator.LTE);
    }

    /**
     * 大于等于
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static QueryCriteria gte(String fieldName, Object value) {
        return new QueryCriteria(fieldName, value, Operator.GTE);
    }


    /**
     * 或者
     * @param criterions ..
     */
    public static LogicalExpression or(QueryCriteria[] criterions) {
        return new LogicalExpression(criterions, Operator.OR);
    }


    /**
     * 区间
     * @param column : 匹配字段
     * @param1 val1 左区间
     * @param2 val2 右区间
     */
    public static LogicalExpression between(String column, Object param1, Object param2) {
        return new LogicalExpression(column, param1, param2, Operator.BETWEEN);
    }


    /**
     * 包含于
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static LogicalExpression in(String fieldName, Collection value) {
        QueryCriteria[] ses = new QueryCriteria[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new QueryCriteria(fieldName, Operator.EQ, obj);
            i++;
        }
        return new LogicalExpression(ses, Operator.OR);
    }

    /**
     * 包含于
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     */
    public static LogicalExpression notIn(String fieldName, Collection value) {
        QueryCriteria[] ses = new QueryCriteria[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new QueryCriteria(fieldName, Operator.NE, obj);
            i++;
        }
        return new LogicalExpression(ses, Operator.AND);
    }
}