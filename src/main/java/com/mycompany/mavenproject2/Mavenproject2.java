<html>
<head>
    <title>Student Enrollment Form</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h2>Student Enrollment Form</h2>
    <form id="studentForm">
        <label for="rollNo">Roll No:</label>
        <input type="text" id="rollNo" name="rollNo" disabled><br><br>
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" disabled><br><br>
        <label for="class">Class:</label>
        <input type="text" id="class" name="class" disabled><br><br>
        <label for="birthDate">Birth Date:</label>
        <input type="date" id="birthDate" name="birthDate" disabled><br><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" disabled><br><br>
        <label for="enrollmentDate">Enrollment Date:</label>
        <input type="date" id="enrollmentDate" name="enrollmentDate" disabled><br><br>

        <button type="button" id="saveBtn" disabled>Save</button>
        <button type="button" id="updateBtn" disabled>Update</button>
        <button type="button" id="resetBtn">Reset</button>
    </form>

    <script>
        const jpdbUrl = "http://api.jsonpowerdb.com:8080/api/";

        // Example function to check if Roll No exists
        function checkRollNoExist(rollNo) {
            $.ajax({
                url: jpdbUrl + "JPDBAPI?" + "query=" + encodeURIComponent("SELECT * FROM STUDENT-TABLE WHERE rollNo='" + rollNo + "'"),
                method: 'GET',
                success: function(response) {
                    if (response.length > 0) {
                        // If Roll No exists, show data in the form and enable Update button
                        $('#fullName').val(response[0].fullName);
                        $('#class').val(response[0].class);
                        $('#birthDate').val(response[0].birthDate);
                        $('#address').val(response[0].address);
                        $('#enrollmentDate').val(response[0].enrollmentDate);
                        $('#rollNo').prop('disabled', true);
                        $('#updateBtn').prop('disabled', false);
                        $('#saveBtn').prop('disabled', true);
                    } else {
                        // If Roll No doesn't exist, enable Save button
                        $('#saveBtn').prop('disabled', false);
                    }
                }
            });
        }

        // Example function to save data
        function saveData() {
            const formData = {
                rollNo: $('#rollNo').val(),
                fullName: $('#fullName').val(),
                class: $('#class').val(),
                birthDate: $('#birthDate').val(),
                address: $('#address').val(),
                enrollmentDate: $('#enrollmentDate').val()
            };
            $.ajax({
                url: jpdbUrl + "JPDBAPI",
                method: 'POST',
                data: formData,
                success: function(response) {
                    alert("Data saved successfully!");
                }
            });
        }

        // Event Handlers
        $('#saveBtn').click(saveData);
        $('#resetBtn').click(function() {
            $('#studentForm')[0].reset();
            $('#saveBtn').prop('disabled', true);
            $('#updateBtn').prop('disabled', true);
            $('#rollNo').prop('disabled', false);
        });

        // Trigger check when Roll No is entered
        $('#rollNo').on('blur', function() {
            const rollNo = $(this).val();
            checkRollNoExist(rollNo);
        });
    </script>
</body>
</html>
