//
//  SwiftInteropper.swift
//  iosApp
//
//  Created by Marcel Bloemendaal on 30/10/2025.
//

import Foundation
import ComposeApp

public class SwiftNativeDependencyExample: ExternalConfigNativeDependencyExample {
    public func executeNativeMethod() {
        print("Hello from Swift!")
    }

    public func executeNativeAsyncMethod() async throws -> String {
        sleep(2)
        return "Hello from Swift async!"
    }
}
