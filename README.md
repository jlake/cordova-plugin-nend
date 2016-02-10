# cordova-plugin-nend

This is a cordova plugin, which provides a way to request Nend ads natively from JavaScript.

Forked from https://github.com/TakayukiSakai/cordova-plugin-nend

## Supported Platforms (and SDK version)

* Android : nendSDK for Android ver3.0.4
* iOS : nendSDK for iOS ver3.0.3

## Installation

```
cordova plugin add https://github.com/jlake/cordova-plugin-nend.git
```

## Getting Started

### Banner Ad (both iOS and Android are supported)

```javascript
var options = {};
options.bannerApiKey = "YOUR_API_KEY";
options.bannerSpotId = "YOUR_SPOT_ID";
options.bannerWidth = 300; // optional (default: 320)
options.bannerHeight = 250; // optional (default: 50)
options.bannerBackgroundColor = "0x6096C3"; // background color for banner view (default: "0xFFFFFF")

Nend.setOptions(options); // If you don't call this function, a key and an id for testing will be used instead.
Nend.createBanner(); // Being invoked in "deviceready" event might be good.

// if you wanna remove Ad
Nend.removeBanner();

```

### Interstitial Ad (both iOS and Android are supported)

```javascript
var options = {};
options.interstitialApiKey = "YOUR_API_KEY";
options.interstitialSpotId = "YOUR_SPOT_ID";

Nend.setOptions(options); // If you don't call this function, a key and an id for testing will be used instead.
Nend.createInterstitial(); // Being invoked in "deviceready" event might be good.

// if you wanna remove Ad
Nend.removeInterstitial();
```
