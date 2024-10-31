<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                        <h4 class="widget-title">Tìm kiếm tòa nhà</h4>

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
                                <form:form modelAttribute="modelSearch" action="/admin/building-list" method="get" id="listForm">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <div>
                                                    <label>Tên tòa nhà</label>
                                                    <form:input class="form-control" path="name" placeholder="Nhập tên tòa nhà"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div>
                                                    <label>Diện tích sàn</label>
                                                    <form:input class="form-control" path="floorArea" type="number"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-2">
                                                <div>
                                                    <label>Quận</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="" label="--Chọn Quan--"/>
                                                        <form:options items="${district}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="col-xs-5">
                                                <div>
                                                    <label>Phường</label>
                                                    <form:input class="form-control" path="ward"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-5">
                                                <div>
                                                    <label>Đường</label>
                                                    <form:input class="form-control" path="street"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Số tầng hầm</label>
                                                    <form:input class="form-control" path="numberOfBasement"
                                                                placeholder="Nhập số tầng hầm..."/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Hướng</label>
                                                    <form:input class="form-control" path="direction"
                                                                placeholder="Nhập huớng..."/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Hạng</label>
                                                    <form:input class="form-control" path="level"
                                                                placeholder="Nhập hạng..."/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-3">
                                                <div>
                                                    <label>Diện tích từ</label>
                                                    <form:input class="form-control" path="areaFrom"
                                                                placeholder="Nhập diện tích từ..."/>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div>
                                                    <label>Diện tích đến</label>
                                                    <form:input class="form-control" path="areaTo"
                                                                placeholder="Nhập diện tichs den..."/>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div>
                                                    <label>Giá tiền từ</label>
                                                    <form:input class="form-control" path="rentPriceFrom"
                                                                placeholder="Nhập giá tiền từ..."/>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div>
                                                    <label>Giá tiền đến</label>
                                                    <form:input class="form-control" path="rentPriceTo"
                                                                placeholder="Nhập giá tiền đến..."/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Tên quản lí</label>
                                                    <form:input class="form-control" path="managerName"
                                                                placeholder="Nhập diện tích từ..."/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>SĐT quản lí</label>
                                                    <form:input class="form-control" path="managerPhone"
                                                                placeholder="Nhập diện tích từ..."/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Nhân viên phụ trách</label>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="" label="--Chọn Nhân Viên--"/>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <div>
                                                    <form:checkboxes items="${renttype}" path="typeCode"/>
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
                                                        id="btnSearchBuilding"
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
                        <a href="/admin/building-edit ">
                            <button
                                    class="btn btn-app btn-primary btn-sm"
                                    title="Thêm tòa nhà"
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

                        <button
                                class="btn btn-app btn-danger btn-sm"
                                title="Xóa tòa nhà"
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
                    </div>
                </div>
            </div>
        </div>
        <!-- row -->
        <div class="hr hr-25 hr-double dotted"></div>
        <div class="row">
            <div class="col-xs-12">
                <table
                        id="buildingList"
                        class="table table-striped table-bordered table-hover"
                >
                    <thead>
                    <tr>
                        <th class="center"></th>
                        <th>Tên tòa nhà</th>
                        <th>Địa chỉ</th>
                        <th>Số tầng hầm</th>
                        <th>Tên quản lí</th>
                        <th>SĐT quản lí</th>
                        <th>D.T sàn</th>
                        <th>D.T trống</th>
                        <th>D.T thuê</th>
                        <th>Giá thuê</th>
                        <th>Phí dịch vụ</th>
                        <th>Phí môi giới</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="item" items="${listBuilding}">
                        <tr>
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" value=${item.id}/>
                                    <span class="lbl"></span>
                                </label>
                            </td>
                            <td>${item.name}</td>
                            <td>${item.address}</td>
                            <td>${item.numberOfBasement}</td>
                            <td>${item.managerName}</td>
                            <td>${item.managerPhone}</td>
                            <td>${item.floorArea}</td>
                            <td>${item.emptyArea}</td>
                            <td>${item.rentArea}</td>
                            <td>${item.rentPrice}</td>
                            <td>${item.serviceFee}</td>
                            <td>${item.brokerageFee}</td>
                            <td>
                                <div class="hidden-sm hidden-xs btn-group">
                                    <button class="btn btn-xs btn-success" title="Giao tòa nhà"
                                            onclick="assignmentBuilding(${item.id})">
                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                    </button>
                                    <a href="/admin/building-edit-${item.id}">
                                        <button class="btn btn-xs btn-info" title="Sửa tòa nhà" type="button">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </button>
                                    </a>

                                    <a href="/admin/building-list">
                                        <button class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteBuilding(${item.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>

                                    </a>

                                    <button class="btn btn-xs btn-warning">
                                        <i class="ace-icon fa fa-flag bigger-120"></i>
                                    </button>
                                </div>

                                <div class="hidden-md hidden-lg">
                                    <div class="inline pos-rel">
                                        <button
                                                class="btn btn-minier btn-primary dropdown-toggle"
                                                data-toggle="dropdown"
                                                data-position="auto"
                                        >
                                            <i
                                                    class="ace-icon fa fa-cog icon-only bigger-110"
                                            ></i>
                                        </button>

                                        <ul
                                                class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close"
                                        >
                                            <li>
                                                <a
                                                        href="#"
                                                        class="tooltip-info"
                                                        data-rel="tooltip"
                                                        title=""
                                                        data-original-title="View"
                                                >
                                        <span class="blue">
                                          <i
                                                  class="ace-icon fa fa-search-plus bigger-120"
                                          ></i>
                                        </span>
                                                </a>
                                            </li>

                                            <li>
                                                <a
                                                        href="#"
                                                        class="tooltip-success"
                                                        data-rel="tooltip"
                                                        title=""
                                                        data-original-title="Edit"
                                                >
                                        <span class="green">
                                          <i
                                                  class="ace-icon fa fa-pencil-square-o bigger-120"
                                          ></i>
                                        </span>
                                                </a>
                                            </li>

                                            <li>
                                                <a
                                                        href="#"
                                                        class="tooltip-error"
                                                        data-rel="tooltip"
                                                        title=""
                                                        data-original-title="Delete"
                                                >
                                        <span class="red">
                                          <i
                                                  class="ace-icon fa fa-trash-o bigger-120"
                                          ></i>
                                        </span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- /.span -->
        </div>
        <!-- row  -->
    </div>
</div>
<!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal">
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
                <input type="hidden" id="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btnAssignmentBuilding">Giao
                </button>
                <a href="/admin/building-list">
                    <button type="button" class="btn btn-primary">Đóng</button>
                </a>
            </div>
        </div>
    </div>
</div>
<%--    modal fade--%>

<script>
    // Giao tòa nha`
    function assignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val(buildingId);
        loadStaffs(buildingId)
    }

    function loadStaffs(buildingId) {
        $.ajax({
            url: "/api/buildings/" + buildingId, // api/buildings/1
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

    $('#btnDeleteBuildings').click(function (e) {
        e.preventDefault(); // ngăn chặn thao tác mặc định của trình duyệt
        var buildingIds = $('#buildingList').find('tbody input[type= checkbox]:checked').map(function () {
            return $(this).val().replace('/', '');
        }).get();
        if (buildingIds.length == 0) {
            alert("Chưa chọn tòa nhà cần xóa");
        } else {
            btnDeleteBuilding(buildingIds)
            location.reload();
        }
    })

    function deleteBuilding(buildingId) {
        btnDeleteBuilding(buildingId);
    }

    function btnDeleteBuilding(data) {
        $.ajax({
            url: "/api/buildings/" + data, // api/buildings/1,2,3
            type: "DELETE",
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

    $('#btnSearchBuilding').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    })

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault(e);
        var json = {};
        json['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type= checkbox]:checked').map(function () {
            return $(this).val().replace('/', '');
        }).get();
        json['staffs'] = staffs;
        updateAssignmentBuilding(json);
    })

    function updateAssignmentBuilding(json) {
        $.ajax({
            url: "/api/buildings/staffs", // api/buildings/1,2,3
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
