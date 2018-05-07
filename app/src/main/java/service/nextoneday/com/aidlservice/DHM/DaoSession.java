package service.nextoneday.com.aidlservice.DHM;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import service.nextoneday.com.aidlservice.DHM.VarTable;

import service.nextoneday.com.aidlservice.DHM.VarTableDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig varTableDaoConfig;

    private final VarTableDao varTableDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        varTableDaoConfig = daoConfigMap.get(VarTableDao.class).clone();
        varTableDaoConfig.initIdentityScope(type);

        varTableDao = new VarTableDao(varTableDaoConfig, this);

        registerDao(VarTable.class, varTableDao);
    }
    
    public void clear() {
        varTableDaoConfig.clearIdentityScope();
    }

    public VarTableDao getVarTableDao() {
        return varTableDao;
    }

}
