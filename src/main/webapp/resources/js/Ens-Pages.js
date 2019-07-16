var userRating = $(document).find(".ratings").data("user-rating");
var tunes = $(document).find(".tune");



$(document).find(".tune").each(function () {
    var rating = $(this).data("rating-tune");
    if(parseInt(rating) <= parseInt(userRating)) {
        $(this).css("color", "blue");
    }
});