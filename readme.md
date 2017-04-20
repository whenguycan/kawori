
version 0.0.4

移除实体中的枚举属性，统一使用string
枚举统一实现IEnum接口，包含code,text2个方法
供下拉框和数据库使用
下拉框使用注解枚举并使用Scans加载