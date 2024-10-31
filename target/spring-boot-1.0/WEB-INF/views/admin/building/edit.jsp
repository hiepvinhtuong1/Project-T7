<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Thông tin tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Thông tin tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div>
            <!-- /.page-header -->
        </div>
        <!-- /.page-content -->
        <div class="row">
            <div class="col-xs-12">
                <form:form method="GET" modelAttribute="buildingEdit" id="form-edit">
                    <form:hidden path="id"/>
                    <div class="form-group">
                        <label class="col-xs-3">Tên tòa nhà</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Quận</label>
                        <div class="col-xs-3">
                            <form:select path="district" class="form-control">
                                <form:option value="" label="--Chọn Quan--"/>
                                <form:options items="${district}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Phường</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="ward"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Đường</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="street"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Số tầng hầm</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="numberOfBasement"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Diện tích sàn</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="floorArea"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Hướng</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="direction"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Hạng</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="level"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Diện tích thuê</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="rentArea"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Giá thuê</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="rentPrice"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Mô tả giá</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="rentPriceDescription"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Phí dịch vuj </label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="serviceFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Phí ô tô</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="carFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Phí ngoài giờ</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="overtimeFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Tiền điêện</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="electricityFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Đặt cọc</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="deposit"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Thanh toán</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="payment"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Thời hạn thuê</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="rentTime"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Thời hạn trang tri </label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="decorationTime"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Người quản lí </label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="managerName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Sdt Người quản lí </label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="managerPhone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Loại tòa nhà</label>
                        <div class="col-xs-9">
                            <form:checkboxes items="${renttype}" path="typeCode"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3"></label>
                        <div class="col-xs-9">
                            <c:if test="${not empty buildingEdit.id}">
                                <button
                                        type="button"
                                        class="btn btn-primary"
                                        id="btnAddOrUpdateBuilding"
                                >
                                    Sửa thoong tin
                                </button>
                            </c:if>
                            <c:if test="${ empty buildingEdit.id}">
                                  <button
                                            type="button"
                                            class="btn btn-primary"
                                            id="btnAddOrUpdateBuilding"
                                    >
                                        Thêm tòa nhà
                                    </button>
                            </c:if>
                            <a href="/admin/building-list">
                                <button type="button" class="btn btn-warning">
                                    Hủy thao tác
                                </button>
                            </a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<!-- /.main-content -->

<!-- Modal -->
<div
        class="modal fade"
        id="assignmentBuildingModal"
>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">
                    Danh sách nhân viên
                </h5>
                <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close"
                >
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id=simple-table" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr class="center">
                        <th>Chọn</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr class="center">
                        <td>
                            <label class="input"></label>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-dismiss="modal"
                >
                    Close
                </button>
                <button type="button" class="btn btn-primary">
                    Save changes
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $('#btnAddOrUpdateBuilding').click(function () {

        // đưa các field vào mảng
        var formData = $("#form-edit").serializeArray(); // mang cac object
        var jSon = {}; // 1 object
        var typeCode = [];
        $.each(formData, function (i, v) {
            if (v.name == 'typeCode') {
                typeCode.push(v.value);
            } else jSon["" + v.name + ""] = v.value;
        });
        jSon['typeCode'] = typeCode;
        if (typeCode.length == 0) {
            alert("Type code not empty!");
        } else {
            btnAddOrUpdateBuilding(jSon);
            location.replace("/admin/building-list");
        }
    });

    function btnAddOrUpdateBuilding(jSon) {
        $.ajax({
            url: "/api/buildings",
            type: "POST",
            data: JSON.stringify(jSon),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                if (result.message === "Success") {
                    alert("Building added/updated successfully!");
                }
            },
            error: function (result) {
                if (result.responseJSON) {
                    // Nếu có responseJSON thì lấy message và chi tiết lỗi từ đó
                    const response = result.responseJSON;
                    if (response.message === "Field error") {
                        const errorDetails = response.detail ? response.detail.join("\n") : "Unknown error";
                        alert("Errors:\n" + errorDetails);
                    } else {
                        alert("An error occurred: " + response.message);
                    }
                } else {
                    // Nếu không có responseJSON, hiển thị responseText hoặc status
                    const errorMessage = result.responseText || `Status code: ${result.status}`;
                    alert("An unexpected error occurred:\n" + errorMessage);
                }
            }
        });
    }

</script>
</body>
</html>
