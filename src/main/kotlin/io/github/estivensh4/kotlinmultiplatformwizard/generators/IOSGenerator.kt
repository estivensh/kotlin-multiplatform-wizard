package io.github.estivensh4.kotlinmultiplatformwizard.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorEmptyDirectory
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderParams
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderTemplateGroup
import io.github.estivensh4.kotlinmultiplatformwizard.utils.GeneratorTemplateFile

class IOSGenerator(params: BuilderParams) : PlatformGenerator(params) {
    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return listOf(
            GeneratorTemplateFile(
                "${params.composeName}/src/iosMain/kotlin/$packageName/${params.composeName}/MainViewController.kt",
                ftManager.getCodeTemplate(BuilderTemplateGroup.COMPOSE_IOS_MAIN)
            ),
            GeneratorEmptyDirectory("iosApp/iosApp.xcodeproj/project.xcworkspace/xcshareddata/swiftpm/configuration"),
            GeneratorTemplateFile(
                "iosApp/iosApp/ContentView.swift"
            ) {
                """
                    import UIKit
                    import SwiftUI
                    import ComposeApp

                    struct ComposeView: UIViewControllerRepresentable {
                        func makeUIViewController(context: Context) -> UIViewController {
                            MainViewControllerKt.MainViewController()
                        }

                        func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
                    }

                    struct ContentView: View {
                        var body: some View {
                            ComposeView()
                                    .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
                        }
                    }
                """.trimIndent()
            },
            GeneratorTemplateFile(
                "iosApp/iosApp/Assets.xcassets/AppIcon.appiconset/Contents.json"
            ) {
               """
                   {
          "images" : [
            {
              "idiom" : "universal",
              "platform" : "ios",
              "size" : "1024x1024"
            }
          ],
          "info" : {
            "author" : "xcode",
            "version" : 1
          }
        }
               """.trimIndent()
            },
            GeneratorTemplateFile(
                "iosApp/iosApp/Assets.xcassets/AccentColor.colorset/Contents.json"
            ) {
                """
                    {
          "colors" : [
            {
              "idiom" : "universal"
            }
          ],
          "info" : {
            "author" : "xcode",
            "version" : 1
          }
        }
                """.trimIndent()
            },
            GeneratorTemplateFile(
                "iosApp/iosApp/Assets.xcassets/Contents.json"
            ) {
                """
                     {
          "info" : {
            "author" : "xcode",
            "version" : 1
          }
        }
                """.trimIndent()
            },
            GeneratorTemplateFile(
                "iosApp/iosApp/Preview Content/Preview Assets.xcassets/Contents.json"
            ) {
                """
                {
                  "info": {
                    "author": "xcode",
                    "version": 1
                  }
                }
                """.trimIndent()
            },
            GeneratorTemplateFile(
                "iosApp/iosApp/iOSApp.swift",
                ftManager.getCodeTemplate(BuilderTemplateGroup.IOS_IOSAPP)
            ),
            GeneratorTemplateFile(
                "iosApp/Configuration/Config.xcconfig",
                ftManager.getCodeTemplate(BuilderTemplateGroup.IOS_APP_CONFIGURATION)
            ),
            GeneratorTemplateFile(
                "iosApp/iosApp.xcodeproj/project.pbxproj",
                ftManager.getCodeTemplate(BuilderTemplateGroup.IOS_PROJECT)
            ),
            /*GeneratorTemplateFile(
                "iosApp/Podfile"
            ) {
                """
                target 'iosApp' do
                  use_frameworks!
                  platform :ios, '14.1'
                  pod '${params.composeName}', :path => '../${params.composeName}'
                end
                """.trimIndent()
            },*/
            GeneratorTemplateFile(
                "iosApp/iosApp/Info.plist"
            ) {
                """
                    <?xml version="1.0" encoding="UTF-8"?>
                    <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
                    <plist version="1.0">
                    <dict>
                    	<key>CFBundleDevelopmentRegion</key>
                    	<string>${'$'}(DEVELOPMENT_LANGUAGE)</string>
                    	<key>CFBundleExecutable</key>
                    	<string>${'$'}(EXECUTABLE_NAME)</string>
                    	<key>CFBundleIdentifier</key>
                    	<string>${'$'}(PRODUCT_BUNDLE_IDENTIFIER)</string>
                    	<key>CFBundleInfoDictionaryVersion</key>
                    	<string>6.0</string>
                    	<key>CFBundleName</key>
                    	<string>${'$'}(PRODUCT_NAME)</string>
                    	<key>CFBundlePackageType</key>
                    	<string>${'$'}(PRODUCT_BUNDLE_PACKAGE_TYPE)</string>
                    	<key>CFBundleShortVersionString</key>
                    	<string>1.0</string>
                    	<key>CFBundleVersion</key>
                    	<string>1</string>
                    	<key>LSRequiresIPhoneOS</key>
                    	<true/>
                    	<key>CADisableMinimumFrameDurationOnPhone</key>
                    	<true/>
                    	<key>UIApplicationSceneManifest</key>
                    	<dict>
                    		<key>UIApplicationSupportsMultipleScenes</key>
                    		<false/>
                    	</dict>
                    	<key>UILaunchScreen</key>
                    	<dict/>
                    	<key>UIRequiredDeviceCapabilities</key>
                    	<array>
                    		<string>armv7</string>
                    	</array>
                    	<key>UISupportedInterfaceOrientations</key>
                    	<array>
                    		<string>UIInterfaceOrientationPortrait</string>
                    		<string>UIInterfaceOrientationLandscapeLeft</string>
                    		<string>UIInterfaceOrientationLandscapeRight</string>
                    	</array>
                    	<key>UISupportedInterfaceOrientations~ipad</key>
                    	<array>
                    		<string>UIInterfaceOrientationPortrait</string>
                    		<string>UIInterfaceOrientationPortraitUpsideDown</string>
                    		<string>UIInterfaceOrientationLandscapeLeft</string>
                    		<string>UIInterfaceOrientationLandscapeRight</string>
                    	</array>
                    </dict>
                    </plist>

                """.trimIndent()
            }
        )
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> = listOf(
        GeneratorTemplateFile(
            "${params.sharedName}/src/iosMain/kotlin/$packageName/${params.sharedName}/Platform.ios.kt",
            ftManager.getCodeTemplate(
                if (params.compose.useMaterial3)
                    BuilderTemplateGroup.IOS_PLATFORM3
                else
                    BuilderTemplateGroup.IOS_PLATFORM
            )
        )
    )
}
