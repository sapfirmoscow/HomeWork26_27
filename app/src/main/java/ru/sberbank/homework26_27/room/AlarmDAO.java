package ru.sberbank.homework26_27.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import ru.sberbank.homework26_27.Alarm;

@Dao
public interface AlarmDAO {

    @Query("select * from Alarm")
    List<Alarm> getAlarms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Alarm alarm);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Alarm alarm);

    @Delete
    void delete(Alarm alarm);

    @Query("select * from Alarm where mEnabled = 1")
    List<Alarm> getEnabledAlarms();

}
