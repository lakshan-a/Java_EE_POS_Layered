// let orderNumberCounter = 1;
// let orderIDstor;
//
// generateorderID();
// function generateorderID() {
//     const orderId = `D${String(orderNumberCounter).padStart(3, '0')}`;
//     orderNumberCounter++;
//     orderIDstor = orderId;
//     $("#txtOrderID").val(orderId);
// }

function clearAllbutton() {
    $('#txtOrderID, #txtDate,#selectCusID,#orderCustomerID,#orderCustomerName ,#orderCustomerSalary ,#orderCustomerAddress ,#selectItemCode,#txtItemCode ,#txtItemDescription ,#txtItemPrice ,#txtQTYOnHand ,#txtQty ,#total ,#subtotal,#orderTable').val('');
    finalTotal = 0;
    final = 0;
    $('#btnAddToTable').prop("disabled", true);
}

function clearBill() {
    $('#txtBalance ,#txtCash ,#txtDiscount').val('');
    $('#total ,#subtotal').text('00.0');
    $('#btnAddToTable').prop("disabled", true);
}