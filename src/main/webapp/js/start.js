var SupportObject = Class.extend({
    subscribe: function(event, fx, scope) {
        jQuery(this).on(event, jQuery.proxy(fx, scope));
    },

    fire: function(event, data) {
        jQuery(this).trigger(event, data);
    }

});

DataSource = SupportObject.extend({
    hasNext: function() {
        return false;
    },

    next: function() {
        return null;
    },

    fireUpdate: function() {
        this.fire('updated');
    },

    subscribeToUpdate: function(fx, scope) {
        this.subscribe('updated', fx, scope);
    },

    refresh: function() {

    }
});

ArrayDataSource = DataSource.extend({
    setArray: function(array) {
        this.index = 0;

        this.array = array;

        this.fireUpdate();
    },
    pop:function(){
        var element = this.array[0];
        delete this.array[0];
        return element;
    }
    ,
    hasNext: function() {
        return this.index < this.array.length;
    },

    next: function() {
        return this.array[this.index++];
    },

    refresh: function() {
        this.setArray(this.array);
    }
});

AjaxDataSource = ArrayDataSource.extend({
    init: function(url) {
        this.url = url;
    },

    load: function() {
        jQuery.ajax({
            url: this.url,
            context: this
        }).done(function (data) {
                this.setArray(data);
        });
    },

    refresh: function() {
        this.load();
    }
});

Table2 = SupportObject.extend({
    init: function(container, dataSource) {
        this.container = container;

        this.dataSource = dataSource;

        this.dataSource.subscribeToUpdate(this.onDataSourceUpdate, this);

        this.table = jQuery('<table/>').appendTo(this.container);
    },

    addRow: function(row) {
        var tr = jQuery('<tr/>').appendTo(this.table);

        _.each(row, function(value) {
            var td = jQuery('<td/>').appendTo(tr);

            if (jQuery.isFunction(value)) {
                value(td);
            } else {
                td.html(value);
            }
        }, this);
    },

    clearTable: function() {
        this.table.empty();
    },

    refresh: function() {
        this.dataSource.refresh();
    },

    onDataSourceUpdate: function() {
        this.clearTable();

        while(this.dataSource.hasNext()) {
            var row = this.dataSource.next();

            this.addRow(row);
        }
    }
});

var showDepartments = {
    url:"/departments"
}

var createDepartment = {
    url:'createDepartment'
}

var addDepartment = {
    url:"addDepartment"

}




var Table = SupportObject.extend({
    init:function(opt){
     this.opt = opt;
    },
    showDepartments:function(response){
        var data = response;
        var table = $('<table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="800"/>');
        $('#start').html(table);
        table.append("<th>Department Name</th>");
        table.append("<th>Department functions</th>");

        for (var i in data) {
            var tr = $('<tr/>');
            var td = $('<td/>');
            table.append(tr);
            tr.append($('<td>'+data[i].departmentName+'</td>'));
            tr.append(td);
            $('<button >').text('Delete').appendTo(td).on('click',{id:data[i].departmentId},$.proxy(this.deleteDepartment,this));
            $('<button >').text('Edit').appendTo(td).on('click',{id:data[i].departmentId,url:'/editDepartment'},$.proxy(this.createDepartment,this));
            $('<button >').text('Show Employees').appendTo(td).on('click',{id:data[i].departmentId},$.proxy(this.downloadEmployees,this));

        }
        $('<button/>').text("Add Department").appendTo('#start').on('click',{id:null,url:'/createDepartment'}, $.proxy(this.createDepartment,this));

    },
    showEmployees:function(response){
        var departmentId = response.department.departmentId;
        var departmentName = response.department.departmentName;
        var ListEmployee = response.ListEmployee;
        var table = $('<table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="800"/>');
        $('#start').html(table);
        table.append('<tr><th colspan="6"><b>'+departmentName+'</b></th></tr>');
        table.append("<tr><th>Fist Name</th>"+"<th>Last Name</th>"+"<th>Day of Birth</th>"+"<th>Salary</th>"+"<th>Email</th>"+"<th>Employee functions</th></tr>");
        for(var i in ListEmployee){
            var tr = $('<tr/>');
            var td = $('<td/>')
            table.append(tr);
            tr.append($('<td>'+ListEmployee[i].firstName+'</td>'));
            tr.append($('<td>'+ListEmployee[i].lastName+'</td>'));
            tr.append($('<td>'+ListEmployee[i].stringFromDate+'</td>'));
            tr.append($('<td>'+ListEmployee[i].salary+'</td>'));
            tr.append($('<td>'+ListEmployee[i].email+'</td>'));
            tr.append(td);
            $('<button >').text('Delete').appendTo(td).on('click',{employeeId:ListEmployee[i].employeeId,departmentId:departmentId},$.proxy(this.deleteEmployee,this));
            $('<button >').text('Edit').appendTo(td).on('click',{employeeId:ListEmployee[i].employeeId,departmentId:departmentId,url:'/editEmployee'},$.proxy(this.editEmployee,this));
        }
        $('<button/>').text("Main Page").appendTo('#start').on('click', $.proxy(this.downloadDepartments,this));
        $('<button/>').text("Add Employee").appendTo('#start').on('click',{employeeId:null,departmentId:departmentId,url:'/createEmployee'}, $.proxy(this.createEmployee,this));

    },

    downloadDepartments:function(){
        $.ajax({
            url:this.opt.url,
            type:this.opt.type || 'GET',
            data:this.opt.data || {},
            context: this,
            success:function(response){
                this.showDepartments(response);
            }
        })
    },
    createDepartment:function(event){
        $.ajax({
            url:event.data.url,
            type:'POST',
            data:({
                departmentId: event.data.id
            }),
            success:function(response){
                var departmentForm = new Form();
                departmentForm.showDepartmentForm(response);

            }

        })

    },
    deleteDepartment:function(event){


        $.ajax({
        url: "/deleteDepartment",
        type: "POST",
        context:this,
        data: ({
            departmentId: event.data.id
        }),
        success: function () {
            this.downloadDepartments();
        }
    })

    },
    downloadEmployees:function(event){
        $.ajax({
            url:'/showEmployee',
            type:'GET',
            data:({
                departmentId:event.data.id
            }),
            context:this,
            success:function(response){
                this.showEmployees(response)

            }
        })
    }
    ,
    createEmployee:function(event){

        employeeForm.showEmployeeForm({department: {departmentId: event.data.departmentId}});

    },
    editEmployee:function(event){
        $.ajax({

            url:event.data.url,
            type:'POST',
            data:({
                employeeId:event.data.employeeId
            }),
            success:function(response){

                employeeForm.showEmployeeForm(response);

            }
        })
    },
    deleteEmployee:function(event){

        $.ajax({
            url:'/deleteEmployee',
            type:'POST',
            context:this,
            data:({
                employeeId:event.data.employeeId,
                departmentId:event.data.departmentId
            }),
            success:function(response){
                this.showEmployees(response)

            }
        })

    }

});



var Form =SupportObject.extend({

    showDepartmentForm:function(response){
        var form = $('<form/>').attr('id','depForm');
        var table = $('<table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="800"/>')
        var tr  = $('<tr/>')
        $('#start').html(form);
        table.appendTo(form)
        tr.appendTo(table)
        $('<input/>').attr('type', 'hidden').addClass('departmentId').val(response.departmentId).appendTo(tr);
        $('<input>').attr({type:'text',name:'departmentName'}).addClass('departmentName').val(response.departmentName).appendTo($('<td/>').appendTo(tr));
        $('<button>').text('Add Department').appendTo(form).on('click', $.proxy(this.addDepartment,this));
        $('<button>').text('Main Page').appendTo(form).on('click', $.proxy(this.main,this));

    },
    addDepartment:function(){

        $('#depForm').validate({
            rules:{
                departmentName:{
                    required:true,
                    minlength:3,
                    maxlength:30
                }
            },
            messages:{
                departmentName:{
                    required:"Enter department name",
                    minlength:"Name must be from 4 to 30",
                    maxlength:"Name must be from 4 to 30"
                }
            },
            submitHandler:function(){
                $.ajax({
                    url:'/add',
                    type:'POST',
                    data:({
                        departmentId:$('.departmentId').val(),
                        departmentName:$('.departmentName').val()
                    }),
                    success:function(){

                        departmentTable.downloadDepartments();
                    }
                })

            }

        })

    },
    showEmployeeForm:function(response){
        var form = $('<form/>').attr('id','empForm');
        var table = $('<table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="800"/>');
        var tr  = $('<tr/>');
        var br = $('<br/>')
        $('#start').html(form);
        table.appendTo(form);
        $('<input/>').attr('type', 'hidden').addClass('departmentId').val(response.department.departmentId).appendTo(tr);
        $('<input/>').attr('type', 'hidden').addClass('employeeId').val(response.employeeId).appendTo(tr);
        tr.appendTo(table);
        $('<td/>').text('First Name:').appendTo(tr);
        $('<input>').attr({type:'text',name:'firstName'}).addClass('firstName').val(response.firstName).appendTo($('<td/>').appendTo(tr));
        var tr  = $('<tr/>');
        tr.appendTo(table);
        $('<td/>').text('Last Name:').appendTo(tr);
        $('<input>').attr({type:'text',name:'lastName'}).addClass('lastName').val(response.lastName).appendTo($('<td/>').appendTo(tr));
        var tr  = $('<tr/>');
        tr.appendTo(table);
        $('<td/>').text('Day of Birth:').appendTo(tr);
        $('<input>').attr({type:'text',name:'dayOfBirth'}).addClass('dayOfBirth').val(response.stringFromDate).appendTo($('<td/>').appendTo(tr));
        var tr  = $('<tr/>');
        tr.appendTo(table);
        $('<td/>').text('Salary:').appendTo(tr);
        $('<input>').attr({type:'text',name:'salary'}).addClass('salary').val(response.salary).appendTo($('<td/>').appendTo(tr));
        var tr  = $('<tr/>');
        tr.appendTo(table);
        $('<td/>').text('Email:').appendTo(tr);
        $('<input>').attr({type:'text',name:'email'}).addClass('email').val(response.email).appendTo($('<td/>').appendTo(tr));
        $('<button>').text('Add Employee').appendTo(form).on('click', $.proxy(this.addEmployee,this));
        $('<button>').text('Main Page').appendTo(form).on('click', $.proxy(this.main,this));

    },
    addEmployee:function(){
        $('#empForm').validate({
            rules:{
                firstName:{
                    required:true,
                    minlength:3,
                    maxlength:20
                },
                lastName:{
                    required:true,
                    minlength:3,
                    maxlength:20
                },
                dayOfBirth:{
                    required:true,
                    date:true
                },
                salary:{
                    required:true,
                    number:true
                },
                email:{
                    required:true,
                    email:true
                }
            },
            messages:{
                firstName:{
                    required:'Enter first name',
                    minlength:'First name must be from 4 to 30',
                    maxlength:'First name must be from 4 to 30'
                },
                lastName:{
                    required:'Enter last name',
                    minlength:'Last name must be from 4 to 30',
                    maxlength:'Last name must be from 4 to 30'
                },
                dayOfBirth:{
                    required:'Enter day of birth',
                    date:'Date format must be "yyyy-MM-dd"'
                },
                salary:{
                    required:'Enter salary',
                    number:'Must be numbers'
                },
                email:{
                    required:'Enter email',
                    email:'email format is name@domain.com'
                }

            },
            submitHandler:function(){
                $.ajax({
                    url:"/addEmployee",
                    type:'POST',
                    data:({
                        employeeId:$('.employeeId').val(),
                        firstName:$('.firstName').val(),
                        lastName:$('.lastName').val(),
                        dayOfBirth:$('.dayOfBirth').val(),
                        salary:$('.salary').val(),
                        email:$('.email').val(),
                        departmentId:$('.departmentId').val()
                    }),
                    success:function(response){
                        departmentTable.showEmployees(response);

                    }
                })

            }
        })
    },
    main:function(){
        departmentTable.downloadDepartments();
    }

});

var employeeForm = new Form();
var departmentTable = new  Table(showDepartments);
$(document).ready(function(){

   departmentTable.downloadDepartments();


})




































//var departments = function () {
//    $.ajax({
//        url: "/departments",
//        type: "GET",
//        success: function (response) {
//            showDepartments(response);
//        }
//    });
//
//};
//
//var showDepartments = function (response) {
//    var table = $('<table style="margin: auto" cellspacing="2" border="1" cellpadding="5" width="600"/>');
//    $('#start').html(table);
//    table.append("<th>Department Name</th>");
//    table.append("<th>Department functions</th>");
//
//
//    for (var i in response) {
//        table.append("<tr><td>" + response[i].departmentName + "</td>" + "<td>" +
//            "<button class='editDepartment' value='" + response[i].departmentId + "'>Edit Department</button>" +
//            "<button class='deleteDepartment' value='" + response[i].departmentId + "'>Delete Department</button>" +
//            "<button class='showEmployees' value='" + response[i].departmentId + "'>Show Employees</button>" +
//            "</td></tr>");
//    }
//
//    $('<button/>').addClass('createDepartment').text("Add Department").appendTo('#start');
//
//    $('.deleteDepartment').click(function () {
//        var departmentId = $(this).val();
//        deleteDepartment(departmentId);
//    });
//    $('.createDepartment').click(function () {
//        createDepartment();
//    });
//    $('.editDepartment').click(function () {
//        var departmentId = $(this).val();
//        editDepartment(departmentId);
//    });
//    $('.showEmployees').click(function () {
//        var departmentId = $(this).val();
//        showEmployees(departmentId);
//    });
//
////
//};
//
//var createDepartment = function(){
//
//
//   var form = $('<div/>');
//
//    $('#start').html(form);
//
////    $('<input id="departmentId">').attr({type:'hidden',name:'departmentId'}).addClass('departmentId').appendTo(form);
//    $('<input/>').attr({id: "departmentName", type:'text',name:'departmentName'}).addClass('departmentName').appendTo(form);
//    $('<button/>').attr('type','submit').addClass('addDepartment').text('Update or Add Department').appendTo(form).click(function(){
//        $.ajax({
//            url: "addDepartment",
//            type: "GET",
//            data: ({
//                departmentName: $(".departmentName").val()
//            }),
//            success: function () {
//                departments();
//            }
//        });
//
//    });
//
//    $('<button/>').addClass('main').text('Main Page').appendTo('#start');
////    validateDep();
//    $('button.main').click(function(){
//        departments();
//    });
//}
//
//var deleteDepartment = function(departmentId){
//
//    $.ajax({
//        url: "/deleteDepartment",
//        type: "GET",
//        data: ({
//            depId: departmentId
//        }),
//        success: function () {
//            departments();
//        }
//    })
//
//
//}
//var editDepartment = function(departmentId){
//
//    $.ajax({
//        url:"/editDepartment",
//        type:"GET",
//        data:({
//            departmentId: departmentId
//        }),
//        success:function(response){
//            var form = $('<div/>');
//            $('#start').append(form);
//            $('<input>').attr({id:departmentId})
//        }
//    })
//
//
//}
//
//var showEmployees = function(departmentId){
//
//}
//
//
//
//
//
//$(document).ready(function(){
//    departments();
//
//})

//var validateDep = function () {
//    $('#form').validate({
//        rules: {
//            departmentName: {
//                required: true,
//                minlength: 4,
//                maxlength: 20
//            }
//        },
//        messages: {
//            departmentName: {
//                required: "Input your name",
//                minLength: "Input value must be 4-10 symbols",
//                maxlength: "Input value must be 4-10 symbols"
//            }
//        },
//        submitHandler: function () {
//            $.ajax({
//                url: "/addDepartment",
//                type: "POST",
//                data: ({
//                    departmentName: $(".departmentName").val()
//                }),
//                success: function () {
//                    departments();
//                }
//            });
//        }
//    });
//};