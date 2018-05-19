package id.ac.uim.kamusmadura.data.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import id.ac.uim.kamusmadura.data.model.KamusModel;

public class KamusHelper {

    private static String MADURA = DatabaseHelper.TABLE_MADURA;

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public Cursor searchQueryByName(String query) {
        String DATABASE_TABLE = MADURA;
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE +
                " WHERE " + DatabaseHelper.FIELD_WORD + " LIKE '%" + query.trim() + "%'", null);
    }

    public ArrayList<KamusModel> getDataByName(String search) {
        KamusModel kamusModel;

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        Cursor cursor = searchQueryByName(search);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_WORD)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TRANSLATE)));
                arrayList.add(kamusModel);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public String getData(String search) {
        String result = "";
        Cursor cursor = searchQueryByName(search);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            result = cursor.getString(2);
            for (; !cursor.isAfterLast(); cursor.moveToNext()) {
                result = cursor.getString(2);
            }
        }
        cursor.close();
        return result;
    }

    public Cursor queryAllData() {
        String DATABASE_TABLE = MADURA;
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + DatabaseHelper.FIELD_ID + " ASC", null);
    }

    public ArrayList<KamusModel> getAllData() {
        KamusModel kamusModel;

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        Cursor cursor = queryAllData();

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_WORD)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TRANSLATE)));
                arrayList.add(kamusModel);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KamusModel kamusModel) {
        String DATABASE_TABLE = MADURA;
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.FIELD_WORD, kamusModel.getWord());
        initialValues.put(DatabaseHelper.FIELD_TRANSLATE, kamusModel.getTranslate());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public void insertTransaction(ArrayList<KamusModel> kamusModels) {
        String DATABASE_TABLE = MADURA;
        String sql = "INSERT INTO " + DATABASE_TABLE + " (" +
                DatabaseHelper.FIELD_WORD + ", " +
                DatabaseHelper.FIELD_TRANSLATE + ") VALUES (?, ?)";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);
        for (int i = 0; i < kamusModels.size(); i++) {
            stmt.bindString(1, kamusModels.get(i).getWord());
            stmt.bindString(2, kamusModels.get(i).getTranslate());
            stmt.execute();
            stmt.clearBindings();
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void update(KamusModel kamusModel) {
        String DATABASE_TABLE = MADURA;
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.FIELD_WORD, kamusModel.getWord());
        args.put(DatabaseHelper.FIELD_TRANSLATE, kamusModel.getTranslate());
        database.update(DATABASE_TABLE, args, DatabaseHelper.FIELD_ID + "= '" + kamusModel.getId() + "'", null);
    }

    public void delete(int id) {
        String DATABASE_TABLE = MADURA;
        database.delete(DATABASE_TABLE, DatabaseHelper.FIELD_ID + " = '" + id + "'", null);
    }
}
