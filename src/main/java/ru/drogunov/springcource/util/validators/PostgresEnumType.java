package ru.drogunov.springcource.util.validators;

import org.hibernate.usertype.UserTypeSupport;
import ru.drogunov.springcource.model.Role;

import java.sql.Types;

public class PostgresEnumType extends UserTypeSupport<Role> {
    public PostgresEnumType(Class<?> returnedClass, int jdbcTypeCode) {
        super(Role.class, Types.OTHER);
    }
//public class PostgresEnumType extends EnumType {
    /*@Override
    public void nullSafeSet(PreparedStatement st,
                            Enum value,
                            int index,
                            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        if (isNull(value)) {
            st.setObject(index, null, Types.OTHER);
        } else {
            st.setObject(index, value.name(), Types.OTHER);
        }
    }*/
}
