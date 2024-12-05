<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Thông tin khách hàng</title>
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
                    Thông tin khách hàng
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
                <form:form method="GET" modelAttribute="customerEdit" id="form-edit">
                    <form:hidden path="id"/>
                    <div class="form-group">
                        <label class="col-xs-3">Tên khách hàng</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="fullName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Số Điện Thoại</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="phone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Email</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Tên công ty</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="companyName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Nhu cầu</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="demand"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3">Tình trạng</label>
                        <div class="col-xs-3">
                            <form:select path="status" class="form-control">
                                <form:option value="" label="--Chọn tình trạng--"/>
                                <form:options items="${status}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3"></label>
                        <div class="col-xs-9">
                            <c:if test="${not empty customerEdit.id}">
                                <button
                                        type="button"
                                        class="btn btn-primary"
                                        id="btnAddOrUpdateCustomer"
                                >
                                    Sửa thông tin
                                </button>
                            </c:if>
                            <c:if test="${ empty customerEdit.id}">
                                <button
                                        type="button"
                                        class="btn btn-primary"
                                        id="btnAddOrUpdateCustomer"
                                >
                                    Thêm khách hàng
                                </button>
                            </c:if>
                            <a href="/admin/customer-list">
                                <button type="button" class="btn btn-warning">
                                    Hủy thao tác
                                </button>
                            </a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

        <c:forEach var="item" items="${transactionType}">
            <div class="col-xs-12">
                <h3 class="header smaller lighter bluee">${item.value}</h3>
                <button class="btn btn-lg btn-primary" onclick="btnAddTransaction('${item.key}',${customerEdit.id})">
                    <i class="ace-icon glyphicon glyphicon-plus smaller-80"></i>Add
                </button>
            </div>
            <c:if test="${item.key == 'CSKH'}">
                <div class="col-xs-12">
                    <table id="simple-table-CSKH" class="table table-striped table-bordered">
                        <thead>
                        <th>Ngày tạo</th>
                        <th>Người tạo</th>
                        <th>Ngày sửa</th>
                        <th>Người sửa</th>
                        <th>Chi tiết giao dịch</th>
                        <th>Thao tác</th>
                        </thead>
                        <tbody>
                        <c:forEach var="transaction" items="${transactionCSKH}">
                            <tr>
                                <td>${transaction.createdDate}</td>
                                <td>${transaction.createdBy}</td>
                                <td>${transaction.modifiedDate}</td>
                                <td>${transaction.modifiedBy}</td>
                                <td>${transaction.note}</td>
                                <td>
                                    <button class="btn btn-sm btn-success" title="Chỉnh sửa" onclick="btnUpdateTransaction(${transaction.id}, ${customerEdit.id})">
                                        <i class="ace-icon glyphicon glyphicon-align-justify"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${item.key == 'DDX'}">
                <div class="col-xs-12">
                    <table id="simple-table-CSKH" class="table table-striped table-bordered">
                        <thead>
                        <th>Ngày tạo</th>
                        <th>Người tạo</th>
                        <th>Ngày sửa</th>
                        <th>Người sửa</th>
                        <th>Chi tiết giao dịch</th>
                        <th>Thao tác</th>
                        </thead>
                        <tbody>
                        <c:forEach var="transaction" items="${transactionDDX}">
                            <tr>
                                <td>${transaction.createdDate}</td>
                                <td>${transaction.createdBy}</td>
                                <td>${transaction.modifiedDate}</td>
                                <td>${transaction.modifiedBy}</td>
                                <td>${transaction.note}</td>
                                <td>
                                    <button class="btn btn-sm btn-success" title="Chỉnh sửa" onclick="btnUpdateTransaction(${transaction.id}, ${customerEdit.id},'${transaction.note}')">
                                        <i class="ace-icon glyphicon glyphicon-align-justify"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

        </c:forEach>
    </div>
</div>
<!-- /.main-content -->

<!-- Modal -->
<div
        class="modal fade"
        id="transactionTypeModal"
>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="exampleModalLongTitle">
                    Nhập giao dịch
                </h4>

            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="note" class="col-xs-12 col-sm-3 control-label no-padding-right">Nội dung giao
                        dịch</label>
                    <div class="colxs-12 col-sm-9">
                          <span class="block input-icon input-icon-right">
                                <input type="text" id="note" class="width-100">
                          </span>
                    </div>
                </div>
                <input type="hidden" id="customerId" name="customerId" value="">
                <input type="hidden" id="code" name="code" value="">
                <input type="hidden" id="idTransaction" name="id" value="">
            </div>
            <div class="modal-footer">
                <c:if test="${empty id }">
                    <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">
                        Thêm giao dịch
                    </button>
                </c:if>
                <c:if test="${not empty id}">
                    <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">
                        Sửa giao dịch
                    </button>
                </c:if>
                <button type="button" class="btn btn-default" onclick="reloadPage()">Đóng</button>
            </div>
        </div>
    </div>
</div>


<script>

    function reloadPage() {
        // Tải lại trang hiện tại
        location.reload();
    }

    function btnAddTransaction(code, customerId) {
        $('#transactionTypeModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
    }

    function btnUpdateTransaction(id, customerId,transactionNote) {
        $('#customerId').val(customerId);
        $('#idTransaction').val(id);
        $('#transactionTypeModal').modal();
        $('#note').val(transactionNote);
        //loadDetailTransaction(id);
    }

    function  loadDetailTransaction(transactionId) {
        $.ajax({
            type: "GET",
            url: "/api/customers/transactions/" + transactionId,
            dataType: "json",
            success: (res) => {
                $('#note').val(res.note);
            },
            error: (res) => {
                alert('Failed');
                console.log(res);
            }
        })
    }

    $('#btnAddOrUpdateTransaction').click(function (e) {
        e.preventDefault();
        var data = {};
        data['id'] = $('#idTransaction').val();
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['note'] = $('#note').val();

        handleAddOrUpdateTransaction(data);
    })

    function handleAddOrUpdateTransaction(data) {
        $.ajax({
            url: "/api/customers/transactions",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: (res) => {
                if (data.id) {
                    alert("Sửa giao dịch thành công");
                } else {
                    alert("Thêm giao dịch thành công");
                }
                location.reload();
            },
            error: (res) => {
                alert(res);
                console.log(res);
            }
        })
    }

    $('#btnAddOrUpdateCustomer').click(function (e) {

        e.preventDefault();
        // Lấy giá trị từ các field trong form
        var customerName = $("input[name='fullName']").val();
        var phoneNumber = $("input[name='phone']").val();

        // Kiểm tra tên khách hàng và số điện thoại không được để trống
        if (customerName === "" || phoneNumber === "") {
            alert("Tên khách hàng và Số điện thoại không được để trống!");
            return; // Dừng việc gửi form
        }

        // Kiểm tra số điện thoại phải có 10 chữ số
        var phoneRegex = /^[0-9]{10}$/;
        if (!phoneRegex.test(phoneNumber)) {
            alert("Số điện thoại phải có 10 chữ số!");
            return; // Dừng việc gửi form
        }

        // Kiểm tra tình trạng đã được chọn chưa
        var status = $("select[name='status']").val();
        if (status == undefined || status == null || status.trim() == '') {
            alert("Vui lòng chọn tình trạng!");
            return; // Dừng việc gửi form
        }

        // đưa các field vào mảng
        var data = {}; // 1 object
        var formData = $("#form-edit").serializeArray(); // mang cac object
        $.each(formData, function (i, e) {
            if ('' !== e.value && null != e.value) {
                data['' + e.name + ''] = e.value;
            }
        });
        btnAddOrUpdateCustomer(data);
    });

    function btnAddOrUpdateCustomer(data) {
        $.ajax({
            url: "/api/customers",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                if (result.message === "Success") {
                    alert("Customer added/updated successfully!");
                    location.replace("/admin/customer-list");
                }
            },
            error: function (result) {
                if (result.responseJSON) {
                    const response = result.responseJSON;

                    if (response.message === "Field error") {
                        const errorDetails = response.detail ? response.detail.join("\n") : "Unknown error";
                        alert("Errors:\n" + errorDetails);  // Hiển thị thông tin lỗi chi tiết từ backend
                    } else {
                        alert("An error occurred: " + response.message);
                    }
                } else {
                    const errorMessage = result.responseText || `Status code: ${result.status}`;
                    alert("An unexpected error occurred:\n" + errorMessage);
                }
            }
        });
    }

</script>


</body>
</html>
