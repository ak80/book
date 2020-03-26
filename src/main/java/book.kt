import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.roundToInt

fun main(args: Array<String>) {
    book(357, "all.pdf", true)
}

fun book(pages: Int, name: String, split: Boolean) {
    val correctedPages: Int = round(ceil(pages / 4.0) * 4).roundToInt()
    println("number of pages $pages")
    println("corrected to $correctedPages")
    println()
    println()

    if (!split) {
        print("pdftk A=${name.replace(" ", "\\ ")} B=Blank.pdf cat ")
    }

    print(" ")

    var splitSet = 0;
    // 16,1,2,15,14,3,4,13,12,5,6,11,10,7,8,9,
    for (index in 0..correctedPages step 16) {
        if (split) {
            print("pdftk A=${name.replace(" ", "\\ ")} B=Blank.pdf cat ")
        }
        for (offset in intArrayOf(16, 1, 2, 15, 14, 3, 4, 13, 12, 5, 6, 11, 10, 7, 8, 9)) {
            printPage(index, offset, pages)
        }
        if (split) {
            println(" output out_$splitSet.pdf")
            println("pdfnup out_$splitSet.pdf")
            println()
            splitSet += 1
        }
    }
    if (!split) {
        println(" output out.pdf")
        println("pdfnup out.pdf")
        println()
    }

    // print even then odd: print the even pages, take out of tray and reverse order but leave as is. put in tray
}

fun printPage(index: Int, offset: Int, pages: Int) {
    val page = index + offset;
    if (page <= pages) {
        print("A$page ")
    } else {
        print("B1 ")
    }
}
