package xyz.huanxicloud.findjob.pojo;

import java.util.ArrayList;
import java.util.List;

public class SystemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSystemIdIsNull() {
            addCriterion("system_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("system_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(Integer value) {
            addCriterion("system_id =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(Integer value) {
            addCriterion("system_id <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(Integer value) {
            addCriterion("system_id >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("system_id >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(Integer value) {
            addCriterion("system_id <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(Integer value) {
            addCriterion("system_id <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<Integer> values) {
            addCriterion("system_id in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<Integer> values) {
            addCriterion("system_id not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(Integer value1, Integer value2) {
            addCriterion("system_id between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("system_id not between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSKeyIsNull() {
            addCriterion("s_key is null");
            return (Criteria) this;
        }

        public Criteria andSKeyIsNotNull() {
            addCriterion("s_key is not null");
            return (Criteria) this;
        }

        public Criteria andSKeyEqualTo(String value) {
            addCriterion("s_key =", value, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyNotEqualTo(String value) {
            addCriterion("s_key <>", value, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyGreaterThan(String value) {
            addCriterion("s_key >", value, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyGreaterThanOrEqualTo(String value) {
            addCriterion("s_key >=", value, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyLessThan(String value) {
            addCriterion("s_key <", value, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyLessThanOrEqualTo(String value) {
            addCriterion("s_key <=", value, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyLike(String value) {
            addCriterion("s_key like", value, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyNotLike(String value) {
            addCriterion("s_key not like", value, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyIn(List<String> values) {
            addCriterion("s_key in", values, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyNotIn(List<String> values) {
            addCriterion("s_key not in", values, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyBetween(String value1, String value2) {
            addCriterion("s_key between", value1, value2, "sKey");
            return (Criteria) this;
        }

        public Criteria andSKeyNotBetween(String value1, String value2) {
            addCriterion("s_key not between", value1, value2, "sKey");
            return (Criteria) this;
        }

        public Criteria andSValueIsNull() {
            addCriterion("s_value is null");
            return (Criteria) this;
        }

        public Criteria andSValueIsNotNull() {
            addCriterion("s_value is not null");
            return (Criteria) this;
        }

        public Criteria andSValueEqualTo(String value) {
            addCriterion("s_value =", value, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueNotEqualTo(String value) {
            addCriterion("s_value <>", value, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueGreaterThan(String value) {
            addCriterion("s_value >", value, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueGreaterThanOrEqualTo(String value) {
            addCriterion("s_value >=", value, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueLessThan(String value) {
            addCriterion("s_value <", value, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueLessThanOrEqualTo(String value) {
            addCriterion("s_value <=", value, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueLike(String value) {
            addCriterion("s_value like", value, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueNotLike(String value) {
            addCriterion("s_value not like", value, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueIn(List<String> values) {
            addCriterion("s_value in", values, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueNotIn(List<String> values) {
            addCriterion("s_value not in", values, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueBetween(String value1, String value2) {
            addCriterion("s_value between", value1, value2, "sValue");
            return (Criteria) this;
        }

        public Criteria andSValueNotBetween(String value1, String value2) {
            addCriterion("s_value not between", value1, value2, "sValue");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}