var ctx;
var formFilter;

// $(document).ready(function () {
$(function () {
    // https://stackoverflow.com/a/5064235/548473
    ctx = {
        ajaxUrl: "meals/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    };
    makeEditable();
    initFilter();
});

function initFilter() {
    formFilter = $('#filter');
}

function updateTable() {
    filtered = false;
    formFilter.find('input').each(function () {
        if ($(this).val() != "") {
            filtered = true;
        }
    })
    if (filtered) {
        $.get(ctx.ajaxUrl + "filter", formFilter.serialize(), function (data) {
            ctx.datatableApi.clear().rows.add(data).draw();
        });
    } else {
        $.get(ctx.ajaxUrl, function (data) {
            ctx.datatableApi.clear().rows.add(data).draw();
        });
    }
}

function clearFilter() {
    formFilter[0].reset();
    updateTable();
}