package egovframework.kit.linc.config;

import org.hibernate.dialect.MariaDB103Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class ConcatMariaDialect extends MariaDB103Dialect {

    public ConcatMariaDialect() {
        super();
        registerFunction("GROUP_CONCAT", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }

}
