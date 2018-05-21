var ajaxUrl = "/ajax/admin/meals/";
var datatableApi;
var globalMenuId = document.getElementById('menuIdValue').value;

function updateTable(menuId) {
    $.get(ajaxUrl + "?menuId=" + menuId, updateTableByData);
}

$(function () {
    datatableApi = $("#mealDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl + "?menuId=" + globalMenuId,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "language": {
        "search": i18n["common.search"]},
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "price"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderUpdateBtn
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

function addMeal(menuId) {
    $("#modalTitle").html(i18n["addTitle"]);
    $("#detailsForm").find(":input").val("");
    $("#editRow").modal().find('#menuId').val(menuId);
}

function saveMeal(menuId) {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize()
    }).done(function () {
            $("#editRow").modal("hide");
            updateTable(menuId);
            successNoty("common.saved");
        }
    );
}

function updateMealsRow(id, menuId) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id + "?menuId=" + menuId, function (data) {

        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });

        $('#editRow').modal().find('#menuId').val(menuId);
    });
}

function deleteMeal(mealId, menuId) {
    $.ajax({
        url: ajaxUrl + mealId,
        type: "DELETE",
        data: {"menuId": menuId}
    }).done(function () {
            updateTable(menuId);
            successNoty("common.deleted");
        }
    );
}

function redirectToMenus(restId) {
    document.location.href = "menus/?restId=" + restId;
}

function renderUpdateBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateMealsRow(" + row.id + "," + globalMenuId + ");'>" +
            "<span class='fa fa-pencil' aria-hidden='true'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteMeal(" + row.id + "," + globalMenuId + ");'>" +
            "<span class='fa fa-remove' aria-hidden='true'></span></a>";
    }
}