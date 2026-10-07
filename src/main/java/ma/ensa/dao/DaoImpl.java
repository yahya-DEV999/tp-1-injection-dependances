package ma.ensa.dao;

import ma.ensa.framework.annotations.Component;

@Component("dao")
public class DaoImpl implements IDao {

    @Override
    public double getData() {
        return 100;
    }
}