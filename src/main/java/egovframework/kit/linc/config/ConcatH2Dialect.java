package egovframework.kit.linc.config;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;


public class ConcatH2Dialect extends H2Dialect {

    public ConcatH2Dialect() {
        super();
        registerFunction("GROUP_CONCAT", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
