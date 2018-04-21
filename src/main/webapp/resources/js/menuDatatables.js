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
                "data": "addDate"
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

function redirectToMeals(restId, menuId) {
    document.location.href = "meals?menuId=" + menuId + "&restId=" + restId;
}

function redirectToRestaurants() {
    document.location.href = "restaurants";
}

function deleteMenu(menuId, restId) {
    $.ajax({
        url: ajaxUrl + menuId,
        type: "DELETE",
        data: {"restId": restId},
        success: function () {
            updateTable(restId);
            successNoty("Deleted");
        }
    });
}

function createMenu(restId) {
    $.ajax({
        url: ajaxUrl,
        type: "POST",
        data: {"restId": restId},
        success: function () {
            updateTable(restId);
            successNoty("Created");
        }
    });
}