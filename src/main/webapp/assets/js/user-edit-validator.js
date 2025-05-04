/**
 * 用户注册、用户信息修改时进行校验
 */
$(function() {
    $("#user-edit-form").bootstrapValidator({
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '用户名长度必须在1到20之间'
                    }
                }
            },
            phone:{
                validators:{
                    notEmpty:{
                        message: '手机号不能为空'
                    },
                    regexp:{
                        regexp: /^1(3[0-9]|4[01456789]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\d{8}$/,
                        message: '手机号格式不正确'
                    }
                }
            },
            email:{

            },
            password: {
                validators: {
                    // notEmpty: {
                    //     message: '密码不能为空'
                    // },   可以维持原密码不变
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: '密码长度必须在5到30之间'
                    },
                    different: { // 不能和用户名相同
                        field: 'username',
                        message: '不能和用户名相同'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码由数字字母下划线和.组成'
                    }
                }
            },
            newpassword: { // 用于account-edit-user.jsp
                validators: {
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: '密码长度必须在5到30之间'
                    },
                    different: { // 不能和用户名相同
                        field: 'username',
                        message: '不能和用户名相同'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码由数字字母下划线和.组成'
                    }
                }
            },
            cpassword: { // 用于account-edit-user.jsp
                validators: {
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: '密码长度必须在5到30之间'
                    },
                    identical: { // 相同
                        field: 'newpassword',
                        message: '两次密码不一致'
                    },
                    different: { // 不能和用户名相同
                        field: 'username',
                        message: '不能和用户名相同'
                    },
                    regexp: { // 匹配规则
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码由数字字母下划线和.组成'
                    }
                }
            },
        }
    });
});