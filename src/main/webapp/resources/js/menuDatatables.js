var ajaxUrl = "/ajax/admin/menus/";
var datatableApi;

function updateTable(restId) {
    $.get(ajaxUrl + "?restId=" + restId, updateTableByData);
}

$(function () {
    datatableApi = $("#menuDatatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            }, {
                "defaultContent": "Edit",
                "orderable": false
            }, {
                "defaultContent": "Vote",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});

function createMenu(restId) {
    $.ajax({
        url: ajaxUrl + "?restId=" + restId,
        type: "POST",
        success: function () {
            updateTable(restId);
            successNoty("Saved");
        }
    });
}

function redirectToMeals(restId, menuId) {
    document.location.href = "meals?menuId=" + menuId + "&restId=" + restId;
}

function deleteMenu(menuId, restId) {
    $.ajax({
        url: ajaxUrl + menuId + "?restId=" + restId,
        type: "DELETE",
        success: function () {
            updateTable(restId);
            successNoty("Deleted");
        }
    });
}