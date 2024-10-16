<div dir="rtl" >




# مستند SDK

## فهرست مطالب
- [مقدمه](#مقدمه)
- [پیش‌نیاز](#پیشنیاز)
- [فعال‌سازی SDK](#فعالسازی-sdk)
- [متد init](#متد-init)
- [انجام تراکنش خرید](#انجام-تراکنش-خرید)
- [درخواست تاییدیه تراکنش خرید](#درخواست-تاییدیه-تراکنش-خرید)
- [درخواست اصلاحیه تراکنش خرید](#درخواست-اصلاحیه-تراکنش-خرید)
- [درخواست تراکنش خدماتی](#درخواست-تراکنش-خدماتی)
- [استعلام موجودی](#استعلام-موجودی)
- [استعلام اطلاعات تراکنش](#استعلام-اطلاعات-تراکنش)
- [درخواست چاپ bitmap](#درخواست-چاپ-bitmap)
- [درخواست انجام پیکربندی](#درخواست-انجام-پیکربندی)
- [استعلام اطلاعات دستگاه](#استعلام-اطلاعات-دستگاه)








## مقدمه
این کلاس شامل متدهای مختلفی است که برای انجام وظایف خاصی مانند تأیید تراکنش‌ها، اصلاحیه تراکنش‌ها، انجام تراکنش‌های خرید و خدماتی، استعلام موجودی حساب و چاپ رسید طراحی شده‌اند. هر متد به‌گونه‌ای طراحی شده است که با جنبه‌های مختلف SDK تعامل داشته باشد و در چارچوب یک Android Activity مورد استفاده قرار گیرد. همچنین این کلاس ارتباط با callbackها را مدیریت می‌کند که بازخوردی از موفقیت یا شکست عملیات ارائه می‌دهند و تضمین می‌کنند که توسعه‌دهندگان می‌توانند به راحتی این ویژگی‌ها را در برنامه‌های خود ادغام کنند.


کلاس `GeneralSDKManager` یک کلاس نهایی (final) است، به این معنا که نمی‌توان آن را subclass کرد و این امر تضمین می‌کند که عملکرد آن ثابت و ایمن باقی بماند. این کلاس همچنین دارای یک مرجع به `HostApp` از طریق کلاس `HostApp` است که با فراخوانی متد `init()` مقداردهی اولیه می‌شود. این مقداردهی اولیه حیاتی است زیرا محیط لازم برای عملکرد مؤثر SDK در برنامه میزبان را فراهم می‌کند.

با استفاده از متدهای ارائه شده توسط `GeneralSDKManager`، توسعه‌دهندگان می‌توانند به طور یکپارچه ThunderSmart SDK را در برنامه‌های خود ادغام کنند و قابلیت‌های متنوعی مرتبط با تراکنش‌ها را فراهم آورند.

## پیش‌نیاز

برای اجرای کدها و مثال‌ها نیاز به اندروید استودیو می‌باشد.


## نرم‌افزار ThunderSmart SDK

<div dir="rtl" style="text-align: justify">
`ThunderSmart SDK` که از طریق کلاس `GeneralSDKManager` قابل دسترسی است، برای تسهیل پردازش امن و کارآمد تراکنش‌ها در برنامه‌های اندرویدی طراحی شده است. این SDK می‌تواند برای فعالیت‌های مختلف مرتبط با پرداخت، از جمله تراکنش‌های خرید، استعلام موجودی و تراکنش‌های خدماتی استفاده شود. با عمل به عنوان یک برنامه third-party، ThunderSmart SDK امکان انجام پرداخت‌های موبایلی امن و یکپارچه را فراهم کرده و به طور مستقیم با برنامه میزبان ادغام می‌شود.
</div>


### فرآیند متدها

#### مقداردهی اولیه
قبل از پردازش هر تراکنش، کلاس `GeneralSDKManager` باید با استفاده از متد `init()` مقداردهی اولیه شود. این متد، تنظیمات لازم را از برنامه میزبان دریافت کرده و SDK را برای عملیات‌های بعدی آماده می‌کند.

#### درخواست تراکنش مالی
برای انجام یک تراکنش، مانند خرید یا خدمات، متد مرتبط مانند `doSaleTransaction` یا `doServiceTransaction` فراخوانی می‌شود. این متد نیاز به پارامترهای خاصی دارد، از جمله جزئیات تراکنش (مانند مبلغ یا نوع درخواست) و یک شیء callback که نتیجه تراکنش را مدیریت می‌کند.

#### پردازش
پس از فراخوانی متدها، SDK درخواست را پردازش می‌کند. بسته به نوع درخواست، SDK یا عملیات را بلافاصله تکمیل می‌کند یا نیاز به اقدامات بیشتر، مانند تأیید کاربر یا تعامل با سیستم‌های خارجی دارد.

#### مدیریت Callback
پس از پردازش تراکنش، نتیجه از طریق رابط callback ارائه‌شده (`TransactionCallBack` یا `ResultCallBack`) برگردانده می‌شود. متدهای callback جزئیات مربوط به موفقیت یا خطاهای احتمالی تراکنش را ارائه می‌دهند.

### نکات مهم

<div dir="rtl">

- **پیاده‌سازی Callback:** پیاده‌سازی صحیح رابط‌های callback ضروری است. اگر callbackهای SDK به درستی مدیریت نشوند، ممکن است تراکنش به درستی به پایان نرسد و دستگاه قادر به انجام تراکنش‌های بعدی نباشد.
- **اصلاحیه تراکنش:** در برخی پیکربندی‌ها، SDK ممکن است اجازه اصلاحیه تراکنش را بر اساس نوع درخواست و تنظیمات متد بدهد یا ندهد. توسعه‌دهندگان باید از این تنظیمات آگاه باشند تا رفتار مورد نظر را در برنامه‌های خود پیاده‌سازی کنند.
  </div>




</div>


<div dir="rtl">

## فعال‌سازی SDK

برای استفاده از این سرویس باید مراحل زیر پیاده‌سازی شوند:

### مرحله 1: کپی کردن فایل‌های SDK

ابتدا، فایل‌های SDK (.jar یا .aar) را در پروژه خود کپی کنید. این فایل‌ها باید در پوشه `libs` پروژه اندرویدی شما قرار گیرند. پوشه `libs` معمولاً در سطح ریشه ماژول شما قرار دارد.

### مرحله 2: افزودن وابستگی به build.gradle

در مرحله بعد، باید وابستگی SDK را در فایل `build.gradle` در سطح ماژول خود اعلام کنید. این کار با استفاده از دستور `implementation` انجام می‌شود که همه فایل‌های `.jar` و `.aar` موجود در پوشه `libs` را شامل می‌شود.

این خط به Gradle می‌گوید که همه فایل‌های `.jar` و `.aar` موجود در پوشه `libs` را به عنوان وابستگی‌های پروژه شامل کند.

پس از انجام این تغییرات، پروژه خود را با Gradle همگام‌سازی کنید. این کار را می‌توانید با کلیک روی "Sync Now" که ظاهر می‌شود یا با استفاده از گزینه "Sync Project with Gradle Files" در Android Studio انجام دهید.
</div>

```gradle
dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar", "*.aar"), "dir" to "libs")))
}
```
<div dir="rtl">

### مرحله 3: مقداردهی اولیه SDK در کد

پس از اضافه کردن فایل‌های SDK و تنظیم وابستگی‌ها، مرحله بعدی مقداردهی اولیه SDK در کد است. این کار باید در مکانی مناسب در برنامه شما انجام شود، مانند main activity یا fragment.
</div>

```kotlin
class MyFragment : Fragment() {

    private lateinit var sdkManager: GeneralSDKManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sdkManager = GeneralSDKManager()
        Log.d("MyFragment", "SDK Manager initialized")
        sdkManager.init(requireActivity())
        Log.d("MyFragment", "SDK Manager init called")
    }
}
```
<div dir="rtl">

متد `init()` روی نمونه `GeneralSDKManager` فراخوانی می‌شود و `requireActivity()` به عنوان context ارسال می‌شود. این context برای مقداردهی اولیه SDK ضروری است.


## متد init

متد `init` برای مقداردهی اولیه ThunderSmart SDK استفاده می‌شود و تنظیمات لازم را برای عملکرد صحیح SDK در برنامه میزبان انجام می‌دهد.

### پارامترهای ورودی `init`

| عنوان  | نوع داده | کاربرد |
| ------ |----------| ------- |
| context | Activity | برنامه یا فعالیتی که برای مقداردهی اولیه SDK استفاده می‌شود. |


### نحوه استفاده از `init`
</div>

```kotlin
val sdkManager = GeneralSDKManager()
sdkManager.init(requireActivity())
```
---
<div dir="rtl">

## انجام تراکنش خرید

متد `doSaleTransaction` برای انجام یک تراکنش خرید با مبلغ مشخص و شماره فاکتور استفاده می‌شود. این متد همچنین اجازه تسویه/اصلاحیه توسط Third Party را می‌دهد و نتیجه تراکنش از طریق یک callback بازگردانده می‌شود.

**نکته:** برای مشخص کردن این‌که تسویه/اصلاحیه توسط Third Party انجام شود یا توسط اپلیکیشن پرداخت Smart Sep، در منوی تنظیمات اپلیکیشن پرداخت Smart Sep و زیر منوی پشتیبان، توسط گزینه "تسویه/اصلاحیه توسط ThirdParty"، قابل تعیین می‌باشد.

### وضعیت فعال گزینه تسویه/اصلاحیه توسط ThirdParty:
حالت‌هایی که شامل ورودی `approveByThird` در متد `doSaleTransaction` می‌شود:
بسته به نوع کسب و کار و یا نوع ارائه خدمات، Third Party می‌تواند تصمیم بگیرد که ارسال تسویه/اصلاحیه به صورت اتوماتیک توسط اپلیکیشن پرداخت Smart Sep و یا توسط خود اپلیکیشن Third Party انجام شود.
- اگر مقدار `true` داده شود، تراکنش انجام شده منتظر انجام عملیات تسویه/اصلاحیه از سمت Third Party باقی می‌ماند و از شروع تراکنش جدید جلوگیری می‌شود.
- اگر مقدار `false` داده شود، ارسال تسویه/اصلاحیه به صورت اتوماتیک توسط اپلیکیشن پرداخت Smart Sep انجام می‌شود، در نتیجه نیاز به عملیاتی از سمت Third Party نخواهد بود، و اپلیکیشن پرداخت آماده شروع تراکنش جدید خواهد بود.

### وضعیت غیرفعال گزینه تسویه/اصلاحیه توسط ThirdParty:
ورودی `approveByThird` در متد `doSaleTransaction` هرچه باشد بی‌تاثیر است و ارسال تسویه/اصلاحیه به صورت اتوماتیک توسط اپلیکیشن پرداخت Smart Sep انجام خواهد شد.

**نکته:** اگر به هر دلیلی تراکنش مالی در اپلیکیشن پرداخت Smart Sep با خطا مواجه شود و مبلغ از دارنده کارت کسر شود، اپلیکیشن پرداخت Smart Sep به صورت اتوماتیک دستور اصلاحیه مبلغ را ارسال خواهد نمود و نیازی به عملیاتی از سمت Third Party نخواهد بود.

</div>
<div dir="rtl">

###  پارامترهای ورودی انجام تراکنش

| عنوان           | نوع داده          | کاربرد |
| --------------- | ----------------- | ------- |
| amount          | String             | مبلغی که باید در تراکنش فروش پردازش شود. |
| reserveNumber   | String             | شماره رزروی که با تراکنش مرتبط است. |
| approveByThird  | Boolean            | یک بولین که نشان می‌دهد آیا تأییدیه توسط third party لازم است یا خیر. |
| transactionCallBack | TransactionCallBack | یک رابط callback که نتیجه تراکنش فروش را مدیریت می‌کند. |

### پارامترهای خروجی انجام تراکنش

پس از انجام تراکنش، نتیجه از طریق `TransactionCallBack` مدیریت می‌شود.

###  نحوه استفاده از انجام تراکنش خرید

</div>

```kotlin
 val transactionCallBack = object : TransactionCallBack {
                    override fun onReceive(transactionData: TransactionData) {
                        Log.i(TAG, "transactionCallBack onReceive: $transactionData")//call toString() of transactionData
                        val intent = Intent(this@MainServicesActivity, ResultActivity::class.java)
                        intent.putExtra(TRANSACTION_DATA, transactionData)
                        startActivity(intent)
                    }

                    override fun onError(errorCode: String, errorMsg: String) {
                        Log.i(TAG, "transactionCallBack onError: $errorCode :  $errorMsg ")
                    }

                    override fun onCanceled() {
                        Log.i(TAG, "transactionCallBack onCanceled: ")
                    }
                }
val amount = "10000"
val reserveNumber = "0123456879"//شناسه پرداخت
sdkManager.doSaleTransaction(amount, reserveNumber, false, transactionCallBack)
```
<div dir="rtl">

## متد `onReceive`:
این متد نتیجه تراکنش (اطلاعات تراکنش) انجام شده را باز می‌گرداند. تراکنش ممکن است موفق یا ناموفق بوده باشد. براساس فیلد `responseCode` از `transactionData`، وضعیت موفق یا ناموفق بودن تراکنش مشخص خواهد شد:
- اگر مقدار `responseCode` برابر "00" باشد، تراکنش موفق است و Third Party بایستی خدمات خود را به مشتری یا کاربر ارائه دهد.
- اگر مقدار `responseCode` هر مقداری غیر از "00" باشد، تراکنش ناموفق است و پیام خطا در فیلد `responseMessage` بازگردانده خواهد شد.

## متد `onUndeterminedStateOfPreviousTxn`:
زمانی فراخوانی می‌شود که تراکنش انجام‌شده موفق قبلی، تعیین تکلیف (تسویه/اصلاحیه) نشده باشد. در این هنگام از شروع تراکنش جدید جلوگیری خواهد شد تا زمانی که تراکنش با اطلاعات بازگردانده شده در این متد، تعیین تکلیف (تسویه/اصلاحیه) شود.
- برای انجام عملیات تسویه تراکنش، از متد `doApprove220()` استفاده نمایید.
- برای انجام عملیات اصلاحیه تراکنش، از متد `doReverse420()` استفاده نمایید.

## متد `onError`:
کد خطا و پیام خطاهای اتفاق افتاده را بازمی‌گرداند. این متد خطاهای عمومی مربوط به اپلیکیشن پرداخت و یا خطاهای پیاده‌سازی SDK توسط Third Party را برمی‌گرداند و فارغ از پیام‌ها و یا خطاهای عملیات انجام تراکنش می‌باشد.

## متد `onCanceled`:
زمانی فراخوانی می‌شود که تراکنش توسط کاربر لغو شده باشد و یا عملیات با Timeout مواجه شود.

</div>

---
<div dir="rtl">

## درخواست تسویه تراکنش خرید

متد `doApprove220` برای پردازش تسویه یک تراکنش خاص بر اساس شماره مرجع بازیابی (RRN) ارائه شده استفاده می‌شود. این متد با سیستم پرداخت تعامل می‌کند تا تراکنش را تأیید کرده و نتیجه را از طریق یک callback بازمی‌گرداند. این متد زمانی کاربرد دارد که اجازه انجام این تراکنش در اپلیکیشن پرداخت توسط سوپروایزر داده شده باشد.

در برخی کسب‌وکارها، Third Party بایستی خدمات خود را پس از انجام پرداخت موفق ارائه نماید. اگر موفق به ارائه خدمات شد، حتماً باید این متد را فراخوانی نماید، در غیراینصورت از شروع تراکنش جدید جلوگیری خواهد شد.


### پارامترهای ورودی درخواست تایید تراکنش

| عنوان  | نوع داده | کاربرد |
| ------ | -------- | ------- |
| rrn     | String   | شماره مرجع بازیابی (RRN) که به طور منحصر به فرد تراکنش مورد نظر برای تأیید را شناسایی می‌کند. |
| resultCallBack | ResultCallBack | یک رابط callback که نتیجه فرآیند تأیید را مدیریت می‌کند. |

### پارامترهای خروجی درخواست تایید تراکنش

پس از درخواست تایید تراکنش، نتیجه عملیات از طریق رابط `ResultCallBack` اطلاع‌رسانی می‌شود.

### نحوه استفاده از درخواست تایید تراکنش

</div>

```kotlin
val resultCallBack = object : ResultCallBack {
    override fun onSuccess() {
        Log.i(TAG, "keyChangeCallBack onSuccess ")
    }

    override fun onError(errorCode: String, errorMsg: String) {
        Log.i(TAG, "keyChangeCallBack onError: $errorCode :  $errorMsg ")
    }
}
sdk.doApprove220(rrn, ResultCallBack )

```
---
<div dir="rtl">

## درخواست اصلاحیه تراکنش خرید

متد `doReverse420` برای پردازش اصلاحیه یک تراکنش خاص با شماره شناسایی استفاده می‌شود. این متد تضمین می‌کند که تراکنش باطل شده و نتیجه از طریق یک callback اطلاع‌رسانی می‌شود.

اگر تراکنش خرید موفق شده باشد و به هر دلیل اپلیکیشن Third Party موفق به ارائه خدمات خود نشود، اپلیکیشن Third Party بایستی تراکنش اصلاحیه را فراخوانی کند و مبلغ به حساب دارنده کارت بازگردد.

### پارامترهای ورودی درخواست اصلاحیه تراکنش

| عنوان   | نوع داده  | کاربرد |
| ------- | --------- | ------- |
| trace   | String    | شماره ردیابی که به طور منحصر به فرد تراکنش مورد نظر برای بازگردانی را شناسایی می‌کند. |
| resultCallBack | ResultCallBack | یک رابط callback برای مدیریت نتیجه فرآیند بازگردانی. |

### پارامترهای خروجی درخواست اصلاحیه تراکنش

پس از درخواست بازگردانی تراکنش، نتیجه عملیات از طریق رابط `ResultCallBack` اطلاع‌رسانی می‌شود.

### نحوه استفاده از درخواست اصلاحیه تراکنش

</div>

```kotlin
val resultCallBack = object : ResultCallBack {
    override fun onSuccess() {
        Log.i(TAG, "keyChangeCallBack onSuccess ")
    }

    override fun onError(errorCode: String, errorMsg: String) {
        Log.i(TAG, "keyChangeCallBack onError: $errorCode :  $errorMsg ")
    }
}
sdk.doReverse420(traceNumber, ResultCallBack) 
```

---




<div dir="rtl">

## درخواست تراکنش خدماتی

متد `doServiceTransaction` برای پردازش یک تراکنش خدماتی بر اساس نوع درخواست خاص استفاده می‌شود. این متد از تأییدیه توسط third party پشتیبانی می‌کند و نتیجه تراکنش از طریق یک callback بازگردانده می‌شود.

### پارامترهای ورودی درخواست تراکنش خدماتی

| عنوان           | نوع داده        | کاربرد |
| --------------- | --------------- | ------- |
| requestType     | RequestType     | نوع درخواست خدماتی که باید پردازش شود. |
| approveByThird  | Boolean         | یک بولین که نشان می‌دهد آیا تأییدیه توسط third party لازم است یا خیر. |
| resultCallBack  | ResultCallBack  | یک رابط callback برای مدیریت نتیجه تراکنش خدماتی. |


**نکته:** در نسخه حال حاضر، در پروژه Sep، تسویه تراکنش‌های خدماتی، توسط اپلیکیشن پرداخت انجام می‌شود و نیازی به ارسال تسویه توسط Third Party نیست.

### پارامترهای خروجی درخواست تراکنش خدماتی

پس از درخواست تراکنش خدماتی، نتیجه عملیات از طریق رابط `ResultCallBack` اطلاع‌رسانی می‌شود.

### نحوه استفاده از درخواست تراکنش خدماتی

</div>

```kotlin
val resultCallBack = object : ResultCallBack {
    override fun onSuccess() {
        Log.i(TAG, "keyChangeCallBack onSuccess ")
    }

    override fun onError(errorCode: String, errorMsg: String) {
        Log.i(TAG, "keyChangeCallBack onError: $errorCode :  $errorMsg ")
    }
}
sdkManager.doServiceTransaction(RequestType.REQUEST_TYPE_CHARGE, false, transactionCallBack)
```

---

<div dir="rtl">

## استعلام موجودی

متد `inquiryBalance` برای درخواست موجودی فعلی مرتبط با یک حساب یا کارت استفاده می‌شود. نتیجه از طریق یک callback ارائه می‌شود.

### پارامترهای ورودی استعلام موجودی

| عنوان               | نوع داده          | کاربرد |
| ------------------- | ----------------- | ------- |
| transactionCallBack | TransactionCallBack | یک رابط callback برای مدیریت نتیجه درخواست موجودی. |

### پارامترهای خروجی استعلام موجودی

پس از استعلام موجودی، نتیجه از طریق `TransactionCallBack` مدیریت می‌شود.

### نحوه استفاده از استعلام موجودی

</div>

```kotlin
val transactionCallBack = object : TransactionCallBack {
    override fun onReceive(transactionData: TransactionData) {
        Log.i(TAG, "transactionCallBack onReceive: $transactionData")//call toString() of transactionData
        val intent = Intent(this@MainServicesActivity, ResultActivity::class.java)
        intent.putExtra(TRANSACTION_DATA, transactionData)
        startActivity(intent)
    }

    override fun onError(errorCode: String, errorMsg: String) {
        Log.i(TAG, "transactionCallBack onError: $errorCode :  $errorMsg ")
    }

    override fun onCanceled() {
        Log.i(TAG, "transactionCallBack onCanceled: ")
    }
}
sdkManager.inquiryBalance(transactionCallBack)
```

---

<div dir="rtl">

## استعلام اطلاعات تراکنش

متد `inquiryTransactionData` برای بازیابی داده‌های یک تراکنش خاص بر اساس نوع استعلام و شناسه استفاده می‌شود. نتیجه به صورت انتخابی چاپ و از طریق یک callback بازگردانده می‌شود.

### پارامترهای ورودی استعلام اطلاعات تراکنش
| عنوان                | نوع داده          | کاربرد                                                     |
|----------------------|-------------------|-------------------------------------------------------------|
| inquiryType          | TxnInquiryType    | نوع inquiry که باید انجام شود. (بر اساس rrn، بر اساس trace، بر اساس reserve number) |
| inquiryId            | String            | با توجه به inquiry type، id را وارد می‌کنیم.                |
| printReceipt         | Boolean           | آیا باید رسید تراکنش چاپ شود یا خیر.                        |
| transactionCallBack  | TransactionCallBack | یک رابط callback برای مدیریت نتیجه پرس‌وجوی تراکنش.       |

## پارامترهای خروجی استعلام اطلاعات تراکنش

پس از استعلام، نتیجه تراکنش از طریق `transactionCallBack` مدیریت می‌شود.

`TxnInquiryType` به صورت enum تعریف شده است که براساس نیاز می‌توانید مقدار آن را تغییر دهید:
- `REQUEST_TYPE_INQUIRY_BY_RRN` : استعلام از طریق شماره مرجع `rrn`
- `REQUEST_TYPE_INQUIRY_BY_TRACE` : استعلام از طریق شماره پیگیری `trace`
- `REQUEST_TYPE_INQUIRY_BY_RESERVE_NUMBER` : استعلام از طریق شماره فاکتور `reserve_number`

### نحوه استفاده از استعلام اطلاعات تراکنش

</div>

```kotlin
val transactionCallBack = object : TransactionCallBack {
    override fun onReceive(transactionData: TransactionData) {
        Log.i(TAG, "transactionCallBack onReceive: $transactionData")//call toString() of transactionData
        val intent = Intent(this@MainServicesActivity, ResultActivity::class.java)
        intent.putExtra(TRANSACTION_DATA, transactionData)
        startActivity(intent)
    }

    override fun onError(errorCode: String, errorMsg: String) {
        Log.i(TAG, "transactionCallBack onError: $errorCode :  $errorMsg ")
    }

    override fun onCanceled() {
        Log.i(TAG, "transactionCallBack onCanceled: ")
    }
}
//شناسه برای استعلام تراکنش
val trace = "69"
val rrn = "320138312569"
val reserveNumber = "123465798"

// TxnInquiryType به صورت enum تعریف شده است، که براساس نیاز میتوانید مقدار آنرا تغییر دهید
val inquiryType = TxnInquiryType.REQUEST_TYPE_INQUIRY_BY_RRN

sdkManager.inquiryTransactionData(inquiryType, rrn, true, transactionCallBack)
```
---

<div dir="rtl">

## درخواست پرینت Bitmap (چاپ رسید)

متد `printBitmap` در Thunder Smart SDK برای چاپ یک تصویر Bitmap به طور مستقیم از یک برنامه اندرویدی استفاده می‌شود. این تابع به توسعه‌دهندگان این امکان را می‌دهد تا یک تصویر را به چاپگر ارسال کنند تا به صورت فیزیکی چاپ شود.

### نکات:
1. لطفاً توجه داشته باشید که عرض فایل Bitmap ارسالی حتماً باید 384 پیکسل باشد. در غیر این صورت، اگر عرض فایل بیشتر یا کمتر از این مقدار باشد، ممکن است چاپ دچار دفرمگی شود.
2. لطفاً توجه داشته باشید که محدودیت‌هایی در حجم و اندازه فایل‌های ارسالی وجود دارد. اگر حجم فایل ارسالی زیاد باشد، ممکن است چاپ نشود و با خطا مواجه شوید.

### پارامترهای ورودی درخواست چاپ bitmap

| عنوان   | نوع داده  | کاربرد |
| ------- | --------- | ------- |
| bitmap  | Bitmap    | تصویر بیت‌مپ که باید چاپ شود. این پارامتر می‌تواند null باشد. |
| resultCallBack | ResultCallBack | یک رابط callback برای مدیریت نتیجه عملیات چاپ. |

### پارامترهای خروجی درخواست چاپ bitmap
پس از درخواست پرینت bitmap، نتیجه عملیات از طریق رابط ResultCallBack اطلاع‌رسانی می‌شود.
### نحوه استفاده از درخواست چاپ bitmap
</div>

```kotlin
val resultCallBack = object : ResultCallBack {
    override fun onSuccess() {
        Log.i(TAG, "keyChangeCallBack onSuccess ")
    }

    override fun onError(errorCode: String, errorMsg: String) {
        Log.i(TAG, "keyChangeCallBack onError: $errorCode :  $errorMsg ")
    }
}
// Attention: width of bitmap must be 384 px
val options = BitmapFactory.Options()
options.inScaled = false
val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.img_384, options)
sdkManager.printBitmap(bitmap, resultCallBack)
```

<div dir="rtl">


---



## درخواست انجام پیکربندی


متد `doConfiguration` برای انجام عملیات پیکربندی اپلیکیشن پرداخت Smart Sep استفاده می‌شود و برای آماده‌سازی آن جهت پردازش تراکنش‌ها به کار می‌رود. نتیجه این پیکربندی از طریق یک callback بازگردانده می‌شود.

**نکته:** در نسخه حال حاضر، نیازی به انجام این عملیات نمی‌باشد و عملیات پیکربندی در منوی تنظیمات و زیر منوی پشتیبان توسط سوپروایزر انجام خواهد گرفت.
### پارامترهای ورودی درخواست انجام پیکربندی

| عنوان  | نوع داده | کاربرد |
| ------ | -------- | ------- |
| resultCallBack | ResultCallBack | یک رابط callback برای مدیریت نتیجه فرآیند پیکربندی. |

### پارامترهای خروجی درخواست انجام پیکربندی

پس از درخواست انجام پیکربندی، نتیجه عملیات از طریق رابط `ResultCallBack` اطلاع‌رسانی می‌شود.

### نحوه استفاده از درخواست انجام پیکربندی

</div>

```kotlin
val resultCallBack = object : ResultCallBack {
    override fun onSuccess() {
        Log.i(TAG, "keyChangeCallBack onSuccess ")
    }

    override fun onError(errorCode: String, errorMsg: String) {
        Log.i(TAG, "keyChangeCallBack onError: $errorCode :  $errorMsg ")
    }
}
sdkManager.doConfiguration(resultCallBack)
```

---




<div dir="rtl">

## استعلام اطلاعات دستگاه

متد `inquiryPosData` برای بازیابی داده‌های Point of Sale (POS) از دستگاه استفاده می‌شود. داده‌های بازیابی شده از طریق یک callback بازگردانده می‌شود.

### پارامترهای ورودی استعلام اطلاعات دستگاه

| عنوان          | نوع داده        | کاربرد |
| -------------- | --------------- | ------- |
| posDataCallBack | PosDataCallBack | یک رابط callback برای مدیریت نتیجه درخواست داده‌های POS. |

### پارامترهای خروجی استعلام اطلاعات دستگاه

پس از استعلام اطلاعات دستگاه، نتیجه از طریق `PosDataCallBack` مدیریت می‌شود.

### نحوه استفاده از استعلام اطلاعات دستگاه

</div>

```kotlin
val posDataCallBack = object : PosDataCallBack {
    override fun onReceive(posData: PosData) {
        Log.i(TAG, "posDataCallBack onReceive: $posData")
        val intent = Intent(this@MainServicesActivity, ResultActivity::class.java)
        intent.putExtra(POS_DATA, posData)
        startActivity(intent)
    }

    override fun onError(errorCode: String, errorMsg: String) {
        Log.i(TAG, "posDataCallBack onError: $errorCode :  $errorMsg ")
    }
}
sdkManager.inquiryPosData(posDataCallBack)
```
---
<p align="center">
<img src="https://github.com/user-attachments/assets/40e0d85f-c37b-4da1-a0b8-7c2c2a200b4f" alt="LOGO_THUNDER_FINAL_FINAL_CDR" width="100"/>
</p>
