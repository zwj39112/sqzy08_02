layui.use(['table', 'form', 'layer'], function () {
    var table = layui.table;
    var form = layui.form;
    var layer = layui.layer, $ = layui.$;

    //执行一个 table 实例
    table.render({
        elem: '#demo'
        , height: 420
        , url: '/sbm/user/userList/' //数据接口
        , id: 'idTest'
        , title: '用户表'
        , page: true //开启分页
        , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , skin: 'row'
        , even: true
        , size: 'lg'
        , limit: 15
        , limits: [15, 30, 45, 60, 75, 90]
        , initSort: {
            field: 'userId' //排序字段，对应 cols 设定的各字段名
            , type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
        }
        , cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'userId', title: '编号', width: '10%', sort: true, fixed: 'left'}
                , {field: 'loginName', title: '登录名', width: '10%', align: 'center'}
                , {field: 'userName', title: '真实姓名', width: '10%', align: 'center'}
                , {
                field: 'sex', title: '性别', width: '10%', align: 'center', templet: function (d) {
                    if (d.sex == 0) {
                        return "男"
                    } else if (d.sex == 1) {
                        return "女"
                    } else {
                        return "未知"
                    }
                }
            }
                , {field: 'phonenumber', title: '手机号', width: '10%', align: 'center'}
                , {field: 'email', title: '邮箱', width: '10%', align: 'center'}
                , {field: 'createBy', title: '创建人', width: '10%', align: 'center'}
                , {field: 'updateBy', title: '修改人', width: '10%', align: 'center'}
                , {
                field: 'status', title: '状态', width: '10%', align: 'center', templet: function (d) {
                    if (d.status == 0) {
                        return "<button class='layui-btn layui-btn-xs layui-btn-radius layui-btn-normal'>正常</button>"
                    } else {
                        return "<button class='layui-btn layui-btn-xs layui-btn-radius layui-btn-danger'>异常</button>"
                    }
                }
            }
                , {
                field: 'deptName', title: '部门名称', width: '10%', align: 'center', templet: function (d) {
                    return d.dept.deptName
                }
            }
                , {fixed: 'right', width: '20%', align: 'center', toolbar: '#barDemo'}
            ]]
        , done: function () {
            $.ajax({
                url: "/sbm/dept/DeptList",
                type: "get",
                dataType: "json",
                success: function (result) {

                    if (result.code == 0) {
                        var temp = result.data;
                        for (var i = 0; i < temp.length; i++) {
                            //动态加入下拉列表
                            $("select[name='deptId']").append("<option value=" + temp[i].deptId + ">" + temp[i].deptName + "</option>");

                        }
                        //刷新select下拉列表
                        form.render("select");
                    }

                }
            });
        }
    });


    //监听头工具栏事件
    table.on('toolbar(tbl)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.msg('添加');
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    unModel(data[0]);
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        }
    });


    //监听行工具事件
    table.on('tool(tbl)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') {
            //删除确认提示框
            layer.confirm('确定删除？', {icon: 3, title: '提示'}, function (index) {
                $.ajax({
                    url: "/sbm/user/oddDelUser",
                    type: "DELETE",
                    data: {"userId": data.userId},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            //关闭弹出层
                            layer.close(index);
                            //刷新页面
                            table.reload('idTest');
                            layer.msg('删除成功');
                        }
                    }
                });
            });

        } else if (layEvent === 'edit') {
            unModel(data);
        }
    });


    //多条件提交按钮监听
    form.on('submit(data-search-btn)', function (data) {
        //获取表单元素值
        var formData = data.field;
        var searchLoginName = formData.searchLoginName;
        var searchUserName = formData.searchUserName;
        var searchPhoneNumber = formData.searchPhoneNumber;

        //重载表格数据
        table.reload('idTest', {
            where: { //设定异步数据接口的额外参数，任意设
                "searchLoginName": searchLoginName,
                "searchUserName": searchUserName,
                "searchPhoneNumber": searchPhoneNumber
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
        return false;//不提交，只传值
    });


    //重置,多条件搜索form表单
    form.on('(search_reset)', function () {
        //重置form表单
        $("#searchForm")[0].reset();

        //刷新页面
        table.reload('idTest');
    });


    var userId;//修改时需要的id
    //打开编辑弹出层
    function unModel(data) {

        userId = data.userId;

        layer.open({
            type: 1,
            area: ['800px', '400px'],
            closeBtn: 2,
            title: ["更新信息", "color:#009688"],
            anim: 4,
            btnAlign: 'c',//按钮居中
            content: $("#model"),
            success: function () {
                //弹出层加载完毕给input框等赋值
                if (data != null && data != '') {

                    //给编辑弹出层表单元素赋值
                    form.val("modelForm", {
                        "userName": data.userName,
                        "sex": data.sex,
                        "phonenumber": data.phonenumber,
                        "email": data.email,
                        "deptId": data.deptId
                    });
                    $(":input[name='status']").val(data.status);

                    //更改switch状态
                    if ($(":input[name='status']").val() == 0) {
                        $('input[name="status"]').prop('checked', true);  //改变开关为 开
                    } else {
                        $('input[name="status"]').prop('checked', false);  //改变开关为 关
                    }

                    //刷新所有表单元素
                    form.render();
                }
            }
        });
    }

    //编辑弹出层submit监听
    form.on('submit(data-model-btn)', function (data) {
        var result = data.field;
        result["updateBy"] = "admin";//模拟修改人
        result["userId"] = userId;//得到需要修改用户id
        
        $.ajax({
            url: url,
            type: "put",
            data: "/sbm/user/updateUser",
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    //关闭弹出层
                    layer.closeAll('page');
                    //刷新页面
                    table.reload('idTest');
                    //刷新select下拉列表
                    form.render();
                }
            }
        });
        return false;
    });

});