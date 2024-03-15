package com.althaus.dev.project04_cartelera.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "genre_ids") val genreIds: List<Int>,
    @ColumnInfo(name = "original_language") val originalLanguage: String?,
    @ColumnInfo(name = "original_title") val originalTitle: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.createIntArray()?.toList() ?: emptyList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
        // Verificar que los campos de tipo String no sean nulos
        requireNotNull(backdropPath)
        requireNotNull(originalLanguage)
        requireNotNull(originalTitle)
        requireNotNull(overview)
        requireNotNull(posterPath)
        requireNotNull(releaseDate)
        requireNotNull(title)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdropPath)
        parcel.writeIntArray(genreIds.toIntArray())
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
