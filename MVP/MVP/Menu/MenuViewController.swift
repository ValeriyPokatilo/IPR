//
//  MenuViewController.swift
//  MVP
//
//  Created by Movavi on 10.11.2021.
//

import UIKit

class MenuViewController: UIViewController {
    
    private var navigation: MenuPresenter.Navigation {
        class NavigationImpl: MenuPresenter.Navigation {

            func openRedScreen() {
                navController.pushViewController(RedViewController(), animated: true)
            }
            
            func openGreenScreen() {
                navController.pushViewController(GreenViewController(), animated: true)
            }
            
            private unowned let navController: UINavigationController
            
            init(navController: UINavigationController) {
                self.navController = navController
            }
        }
        
        if let navi = self.navigationController {
            return NavigationImpl(navController: navi)
        } else {
            return NavigationImpl(navController: UINavigationController())
        }
    }
    
    private lazy var presenter = MenuPresenter(navigation: navigation)

    override func loadView() {
        view = MenuView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let vv = self.view as! MenuView
        let menuView = MenuViewWrapper(view: vv)
        presenter.attachView(view: menuView)
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        presenter.detachView()
    }
}
