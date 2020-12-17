import android.widget.EditText
import android.widget.RadioButton
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car (
    var platNomor : String,
    var namaMobil : String?,
    var merkMobil : String?,
    var kapasitasMesin : Int?,
    var jumlahKursi : Int?,
    var jenisTransmisi : String
): Parcelable
{
    constructor(): this("","", "", 0,0,""){}
}

