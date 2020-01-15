<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>我弄你三，快登录</title>
</head>
<body>
<h1>快登录</h1>
<label>
    <input type="text" name="addrTo" id="addrTo"/>
    <input type="text" name="content" id="content"/>
</label>
<button id="sendBtn">发送</button>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

<script>
    var addr = "${addr}";

    $("#sendBtn").click(function () {
        $.ajax({
            type: "POST",
            async: true,
            url: "/msg/v1/push",
            data: {addrFrom: addr, addrTo: $("#addrTo").val(), content: $("#content").val()},
            success: function (data) {
                if (data.code !== 200) {
                    alert(data.info);
                }
            }
        });
    });

    function pull() {
        $.ajax({
            async: true,
            type: "POST",
            url: "/msg/v1/pull",
            data: {addr: addr},
            success: function (data) {
                if (data.code === 200) {
                    if (data.data && data.data.length > 0) {
                        alert(data.data[0].content);
                    }
                }
                pull();
            }
        });
    }

    pull();
</script>
</body>
</html>