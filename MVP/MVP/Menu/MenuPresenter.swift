//
//  MenuPresenter.swift
//  MVP
//
//  Created by Movavi on 10.11.2021.
//

import Foundation

protocol _MenuNavigation {
    func openRedScreen()
    func openGreenScreen()
}

internal final class MenuPresenter {
    typealias Navigation = _MenuNavigation
    
    private var actionsImpl: MenuViewWrapper.Actions {
        final class MenuActionsImpl: MenuViewWrapper.Actions {

            unowned let parent: MenuPresenter
            
            init(parent: MenuPresenter) {
                self.parent = parent
            }
            
            func onRedButtonClicked() {
                parent.navigation.openRedScreen()
            }
            
            func onGreenButtonClicked() {
                parent.navigation.openGreenScreen()
            }
        }
        return MenuActionsImpl(parent: self)
    }
    
    private let navigation: Navigation
    private var view: MenuViewWrapper? = nil
    
    init(navigation: Navigation) {
        self.navigation = navigation
    }

    func attachView(view: MenuViewWrapper) {
        self.view = view
        view.actions = actionsImpl
    }

    func detachView() {
        view?.actions = nil
        view = nil
    }
}
