android-volley-inputstream-as-response
==================================

Code isn't mine! All credit to [vincestyling](http://stackoverflow.com/users/1294681/vincestyling) on StackOverlow in this question:
http://stackoverflow.com/questions/25258311/how-do-i-get-inpustream-as-response-in-volley-library

It's surprisingly difficult to get responses from Volley as an InputStream. Some people needed this for message pack encoding, I needed it to get an image response as an InputStream so I could do a multipart upload to another URL. I've since come across the [loopj HTTP library](https://github.com/loopj/android-async-http) and use that instead for my usecase as it's much simpler.

The relevant commit with Volley edits is here:
https://github.com/georgiecasey/android-volley-inputstream-as-response/commit/62d9b9132c4f66b2268e9099d418b28884c26ce5

An example usage Activity is in src/com/georgiecasey/inputstreamresponseexampleusage/ExampleUsage.java

