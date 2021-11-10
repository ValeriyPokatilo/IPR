//
//  MenuView.swift
//  MVP
//
//  Created by Movavi on 10.11.2021.
//

import UIKit

internal final class MenuView: UIView {
    
    let redButton: UIButton = {
        let redButton = UIButton(type: .system)
        redButton.setTitle("Red screen", for: .normal)
        redButton.backgroundColor = .white
        redButton.setTitleColor(.black, for: .normal)
        redButton.translatesAutoresizingMaskIntoConstraints = false
        return redButton
    }()
    
    let greenButton: UIButton = {
        let greenButton = UIButton(type: .system)
        greenButton.setTitle("Green screen", for: .normal)
        greenButton.backgroundColor = .white
        greenButton.setTitleColor(.black, for: .normal)
        greenButton.translatesAutoresizingMaskIntoConstraints = false
        return greenButton
    }()
    
    init() {
        super.init(frame: .zero)
        setupUI()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupUI() {
        backgroundColor = .yellow
        addSubview(redButton)
        addSubview(greenButton)
        NSLayoutConstraint.activate([
            redButton.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor, constant: 50),
            redButton.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
            redButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
            greenButton.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor, constant: 100),
            greenButton.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
            greenButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
        ])
    }
}
