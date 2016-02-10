#import <Cordova/CDV.h>

#import "NADView.h"
#import "NADInterstitial.h"

@interface CDVNend : CDVPlugin <NADInterstitialDelegate>

- (void)createBanner:(CDVInvokedUrlCommand *)command;
- (void)removeBanner:(CDVInvokedUrlCommand *)command;
- (void)createInterstitial:(CDVInvokedUrlCommand *)command;
- (void)removeInterstitial:(CDVInvokedUrlCommand *)command;

@end
