<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demo</title>
</head>
<script src="https://ssl.captcha.qq.com/TCaptcha.js"></script>
<body>
</body>
<script>
    var captcha = new TencentCaptcha('1304215962', function(res) {
        /* callback */
        if(res && res.ret === 0) {
            // 获取票据、随机数并调用App端注入的方法传入票据、随机数，进行后台票据校验
            var result = { randstr:res.randstr, ticket:res.ticket };
            window.jsBridge.getData(JSON.stringify(result));
        }
    });
    captcha.show(); // 显示验证码
</script>
</html>
