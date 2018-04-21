var ajaxUrl = "/ajax/admin/meals/";
var datatableApi;

function updateTable(menuId) {
    $.get(ajaxUrl + "?menuId=" + menuId, updateTableByData);
}

$(function () {
    datatableApi = $("#mealDatatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "price"
            },
            {
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

function createMeal(menuId) {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $("#editRow").modal("hide");
            updateTable(menuId);
            successNoty("Saved");
        }
    });
}

$('#editRow').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var menuId = button.data('wherever');
    var modal = $(this);
    modal.find('#menuId').val(menuId);
});

function deleteMeal(mealId, menuId) {
    $.ajax({
        url: ajaxUrl + mealId,
        type: "DELETE",
        data: {"menuId": menuId},
        success: function () {
            updateTable(menuId);
            successNoty("Deleted");
        }
    });
}

function redirectToMenus(restId) {
    document.location.href = "menus/?restId=" + restId;
}
