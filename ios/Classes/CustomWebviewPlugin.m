#import "CustomWebviewPlugin.h"
#import <custom_webview_plugin/custom_webview_plugin-Swift.h>

@implementation CustomWebviewPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftCustomWebviewPlugin registerWithRegistrar:registrar];
}
@end
