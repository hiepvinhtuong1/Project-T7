<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Danh sách Khách Hàng</title>
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
                    Dashboard
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
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Tìm kiếm khách hàng</h4>

                        <span class="widget-toolbar">
                    <a href="#" data-action="reload">
                      <i class="ace-icon fa fa-refresh"></i>
                    </a>

                    <a href="#" data-action="collapse">
                      <i class="ace-icon fa fa-chevron-up"></i>
                    </a>

                    <a href="#" data-action="close">
                      <i class="ace-icon fa fa-times"></i>
                    </a>
                  </span>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="row">
                                <form:form modelAttribute="modelSearch" action="/admin/customer-list" method="get" id="listForm">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Tên khách hàng</label>
                                                    <form:input class="form-control" path="fullName" placeholder="Nhập tên khách hàng"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Số điện thoại</label>
                                                    <form:input class="form-control" path="phone" type="number"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Email</label>
                                                    <form:input class="form-control" path="email" type="number"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Tình trạng</label>
                                                    <form:select path="status" class="form-control">
                                                        <form:option value="" label="--Chọn tình trạng--"/>
                                                        <form:options items="${status}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                             <div class="col-xs-4">
                                                <div>
                                                    <label>Nhân viên phụ trách</label>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="" label="--Chọn nhân viên--"/>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <button
                                                        type="button"
                                                        class="btn btn-sm btn-primary"
                                                        id="btnSearchCustomer"
                                                >
                                                    <i
                                                            class="ace-icon glyphicon glyphicon-search"
                                                    ></i>
                                                    Tìm kiếm
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <div class="pull-right">
                        <a href="/admin/customer-edit ">
                            <button
                                    class="btn btn-app btn-primary btn-sm"
                                    title="Thêm khách hàng"
                            >
                                <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="16"
                                        height="16"
                                        fill="currentColor"
                                        class="bi bi-building-add"
                                        viewBox="0 0 16 16"
                                >
                                    <path
                                            d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"
                                    />
                                    <path
                                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                                    />
                                    <path
                                            d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                                    />
                                </svg>
                            </button>
                        </a>

                        <security:authorize access="hasRole('MANAGER')">
                            <button
                                    class="btn btn-app btn-danger btn-sm"
                                    title="Xóa khách hàng"
                                    id="btnDeleteBuildings"
                            >
                                <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="16"
                                        height="16"
                                        fill="currentColor"
                                        class="bi bi-building-dash"
                                        viewBox="0 0 16 16"
                                >
                                    <path
                                            d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"
                                    />
                                    <path
                                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                                    />
                                    <path
                                            d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                                    />
                                </svg>
                            </button>
                        </security:authorize>
                    </div>
                </div>
            </div>
        </div>
        <!-- row -->
        <div class="hr hr-25 hr-double dotted"></div>
        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="/admin/customer-list" partialList="true" sort="external"
                                   size="${model.totalItems}" defaultorder="ascending"
                                   id="tableList" pagesize="${modelSearch.maxPageItems}"
                                   export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">

                        <display:column title="<fieldset class='form-group'>
									<input type='checkbox' id='checkAll' class='check-box-element'>
									</fieldset>" class="center select-cell"
                                        headerClass="center select-cell">
                            <fieldset>
                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                            </fieldset>
                        </display:column>
                        <display:column headerClass="text-left" property="fullName" title="Tên khách hàng"/>
                        <display:column headerClass="text-left" property="phone" title="Số điện thoại"/>
                        <display:column headerClass="text-left" property="email" title="Email"/>
                        <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                        <display:column headerClass="text-left" property="createdBy" title="Người thêm"/>
                        <display:column headerClass="text-left" property="createdDate" title="Ngày thêm"/>
                        <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                        <display:column headerClass="col-actions" title="Thao tác">
                            <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" title="Giao khách hàng"
                                            onclick="assignmentCustomer(${tableList.id})">
                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                    </button>
                                </security:authorize>


                                <a href="/admin/customer-edit-${tableList.id}">
                                    <button class="btn btn-xs btn-info" title="Sửa khách hàng" type="button">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </button>
                                </a>

                                <security:authorize access="hasRole('MANAGER')">
                                    <a href="/admin/customer-list">
                                        <button class="btn btn-xs btn-danger" title="Xóa khách hàng" onclick="deleteCustomer(${tableList.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </a>
                                </security:authorize>

                                <button class="btn btn-xs btn-warning">
                                    <i class="ace-icon fa fa-flag bigger-120"></i>
                                </button>
                            </div>

                        </display:column>
                    </display:table>
                </div>
            </div>
            <!-- /.span -->
        </div>
        <!-- row  -->
    </div>
</div>
<!-- /.main-content -->

<div class="modal fade" id="assignmentCustomerModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Danh sách nhân viên</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr class="center">
                        <th class="center">Chọn</th>
                        <th class="center">Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btnAssignmentCustomer">Giao
                </button>
                <a href="/admin/customer-list">
                    <button type="button" class="btn btn-primary">Đóng</button>
                </a>
            </div>
        </div>
    </div>
</div>
<%--    modal fade--%>

//<script>
    // Giao tòa nha`
    function assignmentCustomer(customerId) {
        $('#assignmentCustomerModal').modal();
        $('#customerId').val(customerId);
        loadStaffs(customerId);
    }

    function loadStaffs(customerId) {
        $.ajax({
            url: "/api/customers/" + customerId, // api/customers/1
            type: "GET",
            dataType: "json",
            success: function (reponse) {
                var row = '';
                $.each(reponse.data, function (index, item) {
                    row += '<tr >'
                    row += '<td class ="center"> <input type="checkbox" class="check-box-element" value=' + item.staffId + ' id=checkbox_' + item.staffId + ' ' + item.checked + '/></td>'
                    row += '<td class ="center">' + item.userName + '</td>'
                    row += '</tr>'
                })
                $('#staffList tbody').html(row);
                alert(reponse.message);
            },
            error: function (result) {
                console.log("false");
                alert(result.message);
            }
        })
    }

    $('#btnDeleteCustomer').click(function (e) {
        e.preventDefault(); // ngăn chặn thao tác mặc định của trình duyệt
        var customerIds = $('#tableList').find('tbody input[type= checkbox]:checked').map(function () {
            return $(this).val().replace('/', '');
        }).get();
        if (customerIds.length == 0) {
            alert("Chưa chọn tòa nhà cần xóa");
        } else {
            btnDeleteCustomer(customerIds);
        }
    })

    function deleteCustomer(buildingId) {
        btnDeleteCustomer(buildingId);

    }

    function btnDeleteCustomer(data) {
        $.ajax({
            url: "/api/customers/" + data, // api/buildings/1,2,3
            type: "DELETE",
            dataType: "text",
            success: function (result) {
                console.log("success");
                alert("Đã xóa thành công");
                location.reload("/admin/customer-list");
            },
            error: function (result) {
                console.log("false");
                alert(result.message);
            }
        })
    }

    $('#btnSearchCustomer').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    })

    $('#btnAssignmentCustomer').click(function (e) {
        e.preventDefault(e);
        var json = {};
        json['customerId'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type= checkbox]:checked').map(function () {
            return $(this).val().replace('/', '');
        }).get();
        json['staffs'] = staffs;
        updateAssignmentCustomer(json);
    })

    function updateAssignmentCustomer(json) {
        $.ajax({
            url: "/api/customers/staffs", // api/customers/1,2,3
            type: "PUT",
            data: JSON.stringify(json),
            contentType: 'application/json',
            dataType: "text",
            success: function (result) {
                console.log("success");
                alert(result);
            },
            error: function (result) {
                console.log("false");
                alert(result.message);
            }
        })
    }
</script>
</body>
</html>
