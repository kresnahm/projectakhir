import android.widget.EditText
import android.widget.RadioButton
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car (
    var plat_nomor : String,
    var nama : String?,
    var merk : String?,
    var cc : Int?,
    var kursi : Int?,
    var transmisi : String
): Parcelable
{
    constructor(): this("","", "", 0,0,""){}
}

