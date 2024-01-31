$('#btnCusGetAll').on("click",function () {
    Getall();
});

function Getall(){
    $.ajax({
        url : "http://localhost:8080/app/customers",
        method : "GET",
        success : function (resp) {
            // console.log("Success: ", resp);
            console.log(resp[0])
            for (let i=0; i<resp.length; i++) {

                console.log(resp[i].id);
                console.log(resp[i].name);
                console.log(resp[i].address);
                console.log(resp[i].salary);

                const row = `<tr>
                                <td>${resp[i].id}</td>
                                <td>${resp[i].name}</td>
                                <td>${resp[i].address}</td>
                                <td>${resp[i].salary}</td>
                            </tr>`;
                $('#tblCustomer').append(row);
            }

        },
        error : function (error) {
            console.log("error: ", error);
        }
    })
}

$('#btnCusAdd').click(function () {


    const id = $('#txtCustomerID').val();
    const name = $('#txtCustomerName').val();
    const address = $('#txtCustomerAddress').val();
    const salary = $('#txtCustomerSalary').val();

    const customerObj = {
        id:id,
        name:name,
        address:address,
        salary:salary
    };

    const jsonObj = JSON.stringify(customerObj);

    $.ajax({
        url: "http://localhost:8080/app/customers",
        method: "POST",
        data: { id:id,
            name:name,
            address:address,
            salary:salary},

        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);

            Getall();

                alert(jqxhr.responseText);
        },
        error: function (error) {
            console.log("error: ", error);
        }
    })

});

$('#btnCusDelete').click(function () {

    const id = $('#txtCustomerID').val();

    $.ajax({
        url: "http://localhost:8080/app/customers?id=" + id,
        method: "DELETE",
        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
        },
        error: function (error) {
            console.log("error: ", error);
        }
    })


});
$('#btnCusUpdate').click(function () {


    const id = $('#txtCustomerID').val();
    const name = $('#txtCustomerName').val();
    const address = $('#txtCustomerAddress').val();
    const salary = $('#txtCustomerSalary').val();

    const customerObj = {
        id:id,
        name:name,
        address:address,
        salary:salary
    };

    const jsonObj = JSON.stringify(customerObj);

    $.ajax({
        url: "http://localhost:8080/app/customers",
        method: "PUT",
        data: { id:id,
            name:name,
            address:address,
            salary:salary},

        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
        },
        error: function ( error) {
            console.log("error: ", error);
        }
    })
});
