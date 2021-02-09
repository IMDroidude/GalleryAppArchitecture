package gallery.app.architecture.domain.entity

data class PhotoModel(
                       val query : String = "",
                       val pageSize: Int=20,
                       val pageNum: Int=1)