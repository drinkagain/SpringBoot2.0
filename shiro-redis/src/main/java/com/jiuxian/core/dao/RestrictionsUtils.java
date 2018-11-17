package com.jiuxian.core.dao;

import java.util.Collection;

/**
 * 条件构造器
 * 用于创建条件表达式
 */
public class RestrictionsUtils {

    /**
     * 不为空
     *
     * @param fieldName : 匹配字段
     * @return
     */
    public static QueryCondition isNotEmpty(String fieldName) {
        return new QueryCondition(fieldName, Operator.ISNOTEMPTY);
    }

    /**
     * 为空
     *
     * @param fieldName : 匹配字段
     * @return
     */
    public static QueryCondition isEmpty(String fieldName) {
        return new QueryCondition(fieldName, Operator.ISEMPTY);
    }

    /**
     * 为空
     *
     * @param fieldName : 匹配字段
     * @return
     */
    public static QueryCondition isNull(String fieldName) {
        return new QueryCondition(fieldName, Operator.ISNULL);
    }

    /**
     * 不为空
     *
     * @param fieldName : 匹配字段
     * @return
     */
    public static QueryCondition isNotNull(String fieldName) {
        return new QueryCondition(fieldName, Operator.ISNOTNULL);
    }

    /**
     * 等于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static QueryCondition eq(String fieldName, Object value) {
        if (value.equals(null)) return null;
        return new QueryCondition(fieldName, value, Operator.EQ);
    }

    /**
     * 不等于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static QueryCondition ne(String fieldName, Object value) {
        if (value.equals(null)) return null;
        return new QueryCondition(fieldName, value, Operator.NE);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static QueryCondition like(String fieldName, String value) {
        if (value.equals(null)) return null;
        return new QueryCondition(fieldName, value, Operator.LIKE);
    }


    /**
     * 自定义模式模糊匹配
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @param matchMode : 匹配方式(MatchMode.START\END\ANYWHERE)
     * @return
     */
    public static QueryCondition like(String fieldName, String value,
                                      MatchMode matchMode) {
        if (value.equals(null)) return null;
        return new QueryCondition(fieldName, Operator.LIKE, value, matchMode);
    }

    /**
     * 大于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static QueryCondition gt(String fieldName, Object value) {
        if (value.equals(null)) return null;
        return new QueryCondition(fieldName, value, Operator.GT);
    }


    /**
     * 小于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static QueryCondition lt(String fieldName, Object value) {
        if (value.equals(null)) return null;
        return new QueryCondition(fieldName, value, Operator.LT);
    }


    /**
     * 小于等于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static QueryCondition lte(String fieldName, Object value) {
        if (value.equals(null)) return null;
        return new QueryCondition(fieldName, value, Operator.LTE);
    }

    /**
     * 大于等于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static QueryCondition gte(String fieldName, Object value) {
        if (value.equals(null)) return null;
        return new QueryCondition(fieldName, value, Operator.GTE);
    }


    /**
     * 或者
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression or(QueryCondition[] criterions) {
        return new LogicalExpression(criterions, Operator.OR);
    }


    /**
     * 区间
     *
     * @param column : 匹配字段
     * @return
     * @param1 val1 左区间
     * @param2 val2 右区间
     */
    public static LogicalExpression between(String column, Object val1, Object val2) {
        return new LogicalExpression(column, val1, val2, Operator.BETWEEN);
    }


    /**
     * 包含于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static LogicalExpression in(String fieldName, Collection value) {
        QueryCondition[] ses = new QueryCondition[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new QueryCondition(fieldName, Operator.EQ, obj);
            i++;
        }
        return new LogicalExpression(ses, Operator.OR);
    }

    /**
     * 包含于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return
     */
    public static LogicalExpression notIn(String fieldName, Collection value) {
        QueryCondition[] ses = new QueryCondition[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new QueryCondition(fieldName, Operator.NE, obj);
            i++;
        }
        return new LogicalExpression(ses, Operator.AND);
    }
}