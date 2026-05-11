//
//  SwiftNativeViewFactory.swift
//  iosApp
//
//  Created by Marcel Bloemendaal on 04/11/2025.
//

import ComposeApp
import SwiftUI

class SwiftNativeViewFactory : ExternalConfigNativeViewFactory {
    func createButton(text: String, onClick: @escaping () -> Void) -> UIViewController {
        let view = SwiftUINativeButton(label: text, action: onClick)
        return UIHostingController(rootView: view)
    }
}
