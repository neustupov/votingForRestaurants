var ajaxUrl = "/ajax/admin/menus/";
var datatableApi;
var globalRestId = document.getElementById('restIdValue').value;

function updateTable(restId) {
    $.get(ajaxUrl + "?restId=" + restId, updateTableByData);
}

$(function () {
    datatableApi = $("#menuDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl + "?restId=" + globalRestId,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "addDate",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return date.substring(0, 10);
                    }
                    return date;
                }
            }, {
                "orderable": false,
                "defaultContent": "",
                "render": renderAllMealsBtn
            }, {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
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
        data: {"restId": restId}
    }).done(function () {
            updateTable(restId);
            successNoty("common.deleted");
        }
    );
}

function createMenu(restId) {
    $.ajax({
        url: ajaxUrl,
        type: "POST",
        data: {"restId": restId}
    }).done(function () {
            updateTable(restId);
            successNoty("common.saved");
        }
    );
}

function renderAllMealsBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='redirectToMeals(" + globalRestId + "," + row.id + ");'>" +
            "<span class='fa fa-cutlery' aria-hidden='true'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteMenu(" + row.id + "," + globalRestId + ");'>" +
            "<span class='fa fa-remove' aria-hidden='true'></span></a>";
    }
}