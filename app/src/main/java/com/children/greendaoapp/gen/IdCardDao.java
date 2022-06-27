package com.children.greendaoapp.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.children.greendaoapp.entity.IdCard;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ID_CARD".
*/
public class IdCardDao extends AbstractDao<IdCard, String> {

    public static final String TABLENAME = "ID_CARD";

    /**
     * Properties of entity IdCard.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UserName = new Property(0, String.class, "userName", true, "USER_NAME");
        public final static Property IdNo = new Property(1, String.class, "idNo", false, "ID_NO");
    }


    public IdCardDao(DaoConfig config) {
        super(config);
    }
    
    public IdCardDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ID_CARD\" (" + //
                "\"USER_NAME\" TEXT PRIMARY KEY NOT NULL ," + // 0: userName
                "\"ID_NO\" TEXT UNIQUE );"); // 1: idNo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ID_CARD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, IdCard entity) {
        stmt.clearBindings();
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(1, userName);
        }
 
        String idNo = entity.getIdNo();
        if (idNo != null) {
            stmt.bindString(2, idNo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, IdCard entity) {
        stmt.clearBindings();
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(1, userName);
        }
 
        String idNo = entity.getIdNo();
        if (idNo != null) {
            stmt.bindString(2, idNo);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public IdCard readEntity(Cursor cursor, int offset) {
        IdCard entity = new IdCard( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // userName
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // idNo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, IdCard entity, int offset) {
        entity.setUserName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setIdNo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final String updateKeyAfterInsert(IdCard entity, long rowId) {
        return entity.getUserName();
    }
    
    @Override
    public String getKey(IdCard entity) {
        if(entity != null) {
            return entity.getUserName();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(IdCard entity) {
        return entity.getUserName() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
