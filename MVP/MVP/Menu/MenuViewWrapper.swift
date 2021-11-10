//
//  MenuViewWrapper.swift
//  MVP
//
//  Created by Movavi on 10.11.2021.
//

import UIKit

protocol _MenuActions {
    func onRedButtonClicked()
    func onGreenButtonClicked()
}

internal final class MenuViewWrapper {
    
    typealias Actions = _MenuActions
    
    private let view: UIView
    
    var actions: Actions?
    
    init(view: MenuView) {
        self.view = view
        view.redButton.addTarget(self, action: #selector(redTap), for: .touchUpInside)
        view.greenButton.addTarget(self, action: #selector(greenTap), for: .touchUpInside)
    }
    
    @objc func redTap() {
        actions?.onRedButtonClicked()
    }
    
    @objc func greenTap() {
        actions?.onGreenButtonClicked()
    }
}
