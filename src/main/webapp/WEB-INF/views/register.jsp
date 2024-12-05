<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/2/2024
  Time: 8:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng kí</title>
</head>
<body>
<section class="vh-100 gradient-custom form-register">
    <div class="container">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                    <div class="card-body p-2 px-5 text-center">
                        <div class="md-5 md-4 mt-4 pb-2">
                            <h2 class="fw-bold mb-2 text-uppercase">Create an account</h2>
                            <p class="text-white-50 mb-2">Please enter your Information</p>

                            <div class="row">
                                <div class="col-md-6 mb-2">
                                    <div class="form-outline">
                                        <label class="form-label" for="firstName">First name</label>
                                        <input type="text" id="firstName"
                                               class="form-control form-control-lg"/>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-2">
                                    <div class="form-outline">
                                        <label class="form-label" for="lastName">Last name</label>
                                        <input type="text" id="lastName"
                                               class="form-control form-control-lg"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-outline form-white mb-2">
                                <label class="form-label" for="userName">Username</label>
                                <input type="text" id="userName" class="form-control form-control-lg"/>
                            </div>

                            <div class="form-outline form-white mb-2">
                                <label class="form-label" for="password">Password</label>
                                <input type="password" id="password" class="form-control form-control-lg"/>
                            </div>

                            <div class="form-outline form-white mb-2">
                                <label class="form-label" for="repeatedPassword">Repeat your password</label>
                                <input type="password" id="repeatedPassword" class="form-control form-control-lg"/>
                            </div>

                            <div class="form-check d-flex justify-content-center mb-2">
                                <input class="form-check-input me-2" type="checkbox" value=""
                                       id="form2Example3cg"/>
                                <label class="form-check-label">
                                    I agree all statements in <a href="#!" class="text-body"><u
                                        style="color: white ;">Terms of
                                    service</u></a>
                                </label>
                            </div>


                            <button class="btn btn-outline-light btn-lg px-5" type="submit" id="btnAddOrUpdateUsers">
                                Register
                            </button>

                            <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                <a href="#!" class="login-extension text-white"><i
                                        class="fab fa-facebook-f fa-lg"></i></a>
                                <a href="#!" class="login-extension text-white"><i
                                        class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                <a href="#!" class="login-extension text-white"><i
                                        class="fab fa-google fa-lg"></i></a>
                            </div>

                            <p class="text-center text-muted mt-2 mb-0">Have already an account?
                                <a class="nav-link" href="<c:url value='/login'/>">Login here</a></p>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    document.getElementById('btnAddOrUpdateUsers').addEventListener('click', function (event) {
        event.preventDefault(); // Ngăn chặn form submit mặc định (nếu cần)

        // Lấy giá trị từ các ô input
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const userName = document.getElementById('userName').value;
        const password = document.getElementById('password').value;
        const repeatedPassword = document.getElementById('repeatedPassword').value;
        const termsAccepted = document.getElementById('form2Example3cg').checked;

        // Kiểm tra xem tất cả trường có giá trị hợp lệ không
        if (!firstName || !lastName || !userName || !password || !repeatedPassword) {
            alert('Please fill out all required fields.');
            return;
        }

        if (password !== repeatedPassword) {
            alert('Passwords do not match.');
            return;
        }

        if (!termsAccepted) {
            alert('You must agree to the Terms of Service.');
            return;
        }
        let fullName = firstName + lastName;
        let retypePassword = repeatedPassword;
        // Tạo object chứa thông tin người dùng
        const userInfo = {
            fullName,
            userName,
            password,
            retypePassword,
            termsAccepted
        };

        // Log thông tin ra console (hoặc gửi tới server)
        register(userInfo);
    });

    function register(data) {
        $.ajax({
            url: "/api/user",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                alert("Register successfully!");
                location.reload();
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
