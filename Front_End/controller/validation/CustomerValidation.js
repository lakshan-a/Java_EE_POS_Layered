const CUS_ID_REGEX = /^(C00-)[0-9]{3}$/;
const CUS_NAME_REGEX = /^[A-Za-z ]{5,}$/;
const CUS_ADDRESS_REGEX = /^[A-Za-z0-9 ]{8,}$/;
const CUS_SALARY_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;

var validationId;
var validationName;
var validationAddress;
var validationSalary;


$("#btnCusAdd").attr('disabled',true);
$("#btnCusUpdate").attr('disabled',true);

$("#txtCustomerID").keyup(function (e) {
    let value = $("#txtCustomerID").val();
    if (value.length == 0) {
        $("#btnCusAdd").attr('disabled',true);
        $("#txtCustomerID").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_ID_REGEX.test(value);
        if (res) {
            validationId =1;
            setBtn();
            $("#txtCustomerID").css('border', '2px solid green');
        } else {
            $("#txtCustomerID").css('border', '2px solid red');
        }
    }});

$("#txtCustomerName").keyup(function (e) {
    let value = $("#txtCustomerName").val();
    if (value.length == 0) {
        $("#btnCusAdd").attr('disabled',true);
        $("#txtCustomerName").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validationName=1;
            setBtn();
            $("#txtCustomerName").css('border', '2px solid green');
        } else {
            $("#txtCustomerName").css('border', '2px solid red');
        }
    }});

function setBtn() {
    if (validationId==1 && validationName==1){
        $("#btnCusAdd").attr('disabled',false);
        $("#btnCusUpdate").attr('disabled',false);
    }
}
