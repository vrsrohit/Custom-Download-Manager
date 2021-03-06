package com.rohit.customdownloadmanager.database.dao

import androidx.room.*
import com.rohit.customdownloadmanager.database.models.DownloadDetail
import com.rohit.customdownloadmanager.enums.DownloadStatus

@Dao
abstract class DownloadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertDownloadRecord(downloadDetail: DownloadDetail)

    @Query("DELETE FROM DownloadDetail")
    abstract suspend fun deleteAll()

    @Update
    abstract suspend fun updateDownloadDetail(downloadDetail: DownloadDetail)

    @Query("SELECT * FROM DownloadDetail")
    abstract suspend fun getAllDownloads(): List<DownloadDetail>

    @Query("SELECT * FROM DownloadDetail WHERE downloadStatus LIKE :downloadStatus ORDER BY priority DESC")
    abstract suspend fun getPendingDownloads(downloadStatus: DownloadStatus = DownloadStatus.Enqueued): List<DownloadDetail>

    @Query("SELECT * FROM DownloadDetail WHERE downloadStatus LIKE :downloadStatus ORDER BY priority DESC LIMIT 1")
    abstract suspend fun getPendingDownload(downloadStatus: DownloadStatus = DownloadStatus.Enqueued): DownloadDetail?

    @Query("SELECT * FROM DownloadDetail WHERE downloadStatus LIKE :downloadStatus ORDER BY priority ASC ")
    abstract suspend fun getCompletedDownloads(downloadStatus: DownloadStatus = DownloadStatus.Completed): List<DownloadDetail>


}