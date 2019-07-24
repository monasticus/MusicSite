$('#deleteModal').on('show.bs.modal', function (event) {
    var itemId = $(event.relatedTarget).data('item-id');
    var itemName = $(event.relatedTarget).data('item-name');
    var itemType = $(event.relatedTarget).data('item-type');
    $(this).find('.modal-body p #itemName').text(itemName);
    $('#deleteId').on('click', function () {
        if ($(this).text() == "Remove")
            window.location.href = "/adm/" + itemType + "/remove/" + itemId;
        else if ($(this).text() == "Discard")
            window.location.href = "/admgi/propositions/" + itemType + "/discard?id=" + itemId;
    })
});